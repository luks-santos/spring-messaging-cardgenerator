package com.cardgenerator.CreditEvaluator.infra.clients;

import com.cardgenerator.CreditEvaluator.domain.model.Card;
import com.cardgenerator.CreditEvaluator.domain.model.CardClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "card-service", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "income")
    ResponseEntity<List<Card>> getCardsHasIncomeLessThanEqualTo(@RequestParam Long income);

    @GetMapping(params = "cpf")
    ResponseEntity<List<CardClient>> getCardsByClient(@RequestParam String cpf);
}
