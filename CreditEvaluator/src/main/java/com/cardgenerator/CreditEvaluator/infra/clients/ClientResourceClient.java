package com.cardgenerator.CreditEvaluator.infra.clients;

import com.cardgenerator.CreditEvaluator.domain.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client-service", path = "/clients")
public interface ClientResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<Client> getClientByCpf(@RequestParam(value = "cpf") String cpf);
}
