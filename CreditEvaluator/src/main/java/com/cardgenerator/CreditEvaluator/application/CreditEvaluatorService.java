package com.cardgenerator.CreditEvaluator.application;

import com.cardgenerator.CreditEvaluator.application.ex.CardRequestException;
import com.cardgenerator.CreditEvaluator.application.ex.ClientNotFoundException;
import com.cardgenerator.CreditEvaluator.application.ex.MicroserviceCommunicationException;
import com.cardgenerator.CreditEvaluator.domain.model.Card;
import com.cardgenerator.CreditEvaluator.domain.model.CardApproved;
import com.cardgenerator.CreditEvaluator.domain.model.CardClient;
import com.cardgenerator.CreditEvaluator.domain.model.CardRequest;
import com.cardgenerator.CreditEvaluator.domain.model.CardRequestProtocol;
import com.cardgenerator.CreditEvaluator.domain.model.Client;
import com.cardgenerator.CreditEvaluator.domain.model.ClientEvaluation;
import com.cardgenerator.CreditEvaluator.domain.model.ClientStatus;
import com.cardgenerator.CreditEvaluator.infra.clients.CardResourceClient;
import com.cardgenerator.CreditEvaluator.infra.clients.ClientResourceClient;
import com.cardgenerator.CreditEvaluator.infra.mqueue.CardRequestPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {

    private final ClientResourceClient clientClient;
    private final CardResourceClient cardClient;
    private final CardRequestPublisher cardPublisher;

    public ClientStatus getClientStatus(String cpf) {
        try {
            Client client = clientClient.getClientByCpf(cpf).getBody();
            List<CardClient> cards = cardClient.getCardsByClient(cpf).getBody();

            return ClientStatus
                    .builder()
                    .client(client)
                    .cards(cards)
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientNotFoundException();
            }
            throw new MicroserviceCommunicationException(e.getMessage(), status);
        }
    }

    public ClientEvaluation evaluateClient(String cpf, Long income) {
        try {
            Client client = clientClient.getClientByCpf(cpf).getBody();
            List<Card> cards = cardClient.getCardsHasIncomeLessThanEqualTo(income).getBody();

            var cardsApproved = cards.stream().map(c -> {
                BigDecimal creditLimit = c.getCreditLimit();
                BigDecimal age = BigDecimal.valueOf(client.getAge());
                var coefficient = age.divide(BigDecimal.valueOf(10));
                BigDecimal limitReleased = coefficient.multiply(creditLimit);
                return toCardApproved(c, limitReleased);
            }).toList();
            return new ClientEvaluation(cardsApproved);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientNotFoundException();
            }
            throw new MicroserviceCommunicationException(e.getMessage(), status);
        }
    }

    private CardApproved toCardApproved(Card card, BigDecimal limitReleased) {
        return new CardApproved(card.getName(), card.getBrand(), limitReleased);
    }


    public CardRequestProtocol cardEmitRequest(CardRequest data) {
        try {
            cardPublisher.requestCard(data);
            return new CardRequestProtocol(UUID.randomUUID().toString());
        } catch (Exception e) {
            throw new CardRequestException(e.getMessage());
        }
    }
}
