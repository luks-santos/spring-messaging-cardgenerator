package com.cardgenerator.card.infra.repository;

import com.cardgenerator.card.domain.CardClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardClientRepository extends JpaRepository<CardClient, Long> {

    List<CardClient> findByCpf(String cpf);
}
