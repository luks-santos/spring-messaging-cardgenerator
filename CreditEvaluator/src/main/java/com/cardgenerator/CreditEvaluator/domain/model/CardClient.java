package com.cardgenerator.CreditEvaluator.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CardClient {
    private String name;
    private String brand;
    private BigDecimal limitReleased;
}
