package com.cardgenerator.card.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CardClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
    private BigDecimal limitReleased;


    public CardClient(String cpf, Card card, BigDecimal limitReleased) {
        this.cpf = cpf;
        this.card = card;
        this.limitReleased = limitReleased;
    }
}
