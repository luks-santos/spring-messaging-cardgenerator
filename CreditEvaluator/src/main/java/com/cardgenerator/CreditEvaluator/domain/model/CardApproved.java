package com.cardgenerator.CreditEvaluator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardApproved {
    private String name;
    private String brand;
    private BigDecimal limitReleased;
}
