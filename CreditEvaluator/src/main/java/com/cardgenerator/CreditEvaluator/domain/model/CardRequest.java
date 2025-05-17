package com.cardgenerator.CreditEvaluator.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CardRequest {
    private Long cardId;
    private String cpf;
    private BigDecimal limitReleased;
}
