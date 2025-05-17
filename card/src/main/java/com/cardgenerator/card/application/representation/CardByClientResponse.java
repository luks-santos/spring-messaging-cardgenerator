package com.cardgenerator.card.application.representation;

import com.cardgenerator.card.domain.CardClient;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CardByClientResponse {
    private String name;
    private String brand;
    private BigDecimal limit;

    public static CardByClientResponse fromModel(CardClient card) {
        CardByClientResponse response = new CardByClientResponse();
        response.setName(card.getCard().getName());
        response.setBrand(card.getCard().getBrand().toString());
        response.setLimit(card.getLimitReleased());
        return response;
    }
}
