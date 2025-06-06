package com.cardgenerator.card.application;

import com.cardgenerator.card.application.representation.CardByClientResponse;
import com.cardgenerator.card.application.representation.CardSaveRequest;
import com.cardgenerator.card.domain.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
@Slf4j
public class CardResource {

    private final CardService cardService;
    private final CardClientService cardClientService;

    @GetMapping
    public String healthCheck() {
        log.info("Health check client");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CardSaveRequest request) {
        Card card = request.toCard();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsHasIncomeLessThanEqualTo(@RequestParam Long income) {
        List<Card> cards = cardService.getCardsHasIncomeLessThanEqualTo(income);
        return ResponseEntity.ok(cards);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardByClientResponse>> getCardsByClient(@RequestParam String cpf) {
        List<CardByClientResponse> cards = cardClientService.getCardsByCpf(cpf)
                .stream()
                .map(CardByClientResponse::fromModel)
                .toList();
        return ResponseEntity.ok(cards);
    }
}
