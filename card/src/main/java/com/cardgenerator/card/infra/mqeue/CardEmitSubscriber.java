package com.cardgenerator.card.infra.mqeue;

import com.cardgenerator.card.domain.Card;
import com.cardgenerator.card.domain.CardClient;
import com.cardgenerator.card.domain.model.CardRequest;
import com.cardgenerator.card.infra.repository.CardClientRepository;
import com.cardgenerator.card.infra.repository.CardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CardEmitSubscriber {

    private final CardRepository cardRepository;
    private final CardClientRepository cardClientRepository;

    @RabbitListener(queues = "${mq.queues.card-emit}")
    public void receiveRequest(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            CardRequest data = mapper.readValue(payload, CardRequest.class);

            Card card = cardRepository.findById(data.getCardId()).orElseThrow();
            CardClient cardClient = new CardClient(data.getCpf(), card, data.getLimitReleased());

            cardClientRepository.save(cardClient);
        } catch (JsonProcessingException e) {
            log.error("CardEmitSubscriber::receiveRequest", e);
        }
    }
}
