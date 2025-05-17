package com.cardgenerator.card.application;

import com.cardgenerator.card.domain.Card;
import com.cardgenerator.card.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;

    public Card save(Card card) {
        return repository.save(card);
    }

    public List<Card> getCardsHasIncomeLessThanEqualTo(Long income) {
        BigDecimal incomeDecimal = BigDecimal.valueOf(income);
        return repository.findByIncomeLessThanEqual(incomeDecimal);
    }
}
