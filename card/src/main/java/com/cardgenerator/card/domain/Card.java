package com.cardgenerator.card.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CardBrand brand;

    private BigDecimal income;

    private BigDecimal creditLimit;

    public Card(String name, CardBrand brand, BigDecimal income, BigDecimal creditLimit) {
        this.name = name;
        this.brand = brand;
        this.income = income;
        this.creditLimit = creditLimit;
    }
}
