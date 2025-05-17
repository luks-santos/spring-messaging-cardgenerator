package com.cardgenerator.card.application;

import com.cardgenerator.card.domain.CardClient;
import com.cardgenerator.card.infra.repository.CardClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardClientService {

    private final CardClientRepository repository;

    public List<CardClient> getCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
