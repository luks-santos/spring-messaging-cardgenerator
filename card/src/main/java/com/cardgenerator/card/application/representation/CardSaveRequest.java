package com.cardgenerator.card.application.representation;

import com.cardgenerator.card.domain.Card;
import com.cardgenerator.card.domain.CardBrand;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CardSaveRequest {
    private String name;
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal creditLimit;

    public Card toCard() {
        return new Card(this.name, this.brand, this.income, this.creditLimit);
    }
}
