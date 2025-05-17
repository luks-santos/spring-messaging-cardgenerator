package com.cardgenerator.client.application;

import com.cardgenerator.client.domain.Client;
import com.cardgenerator.client.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public Client save(Client client) {
        var clientOp = getByCPF(client.getCpf());
        return clientOp.orElseGet(() -> repository.save(client));
    }

    public Optional<Client> getByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}
