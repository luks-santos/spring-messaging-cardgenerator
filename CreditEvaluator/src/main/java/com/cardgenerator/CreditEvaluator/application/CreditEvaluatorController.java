package com.cardgenerator.CreditEvaluator.application;

import com.cardgenerator.CreditEvaluator.application.ex.ClientNotFoundException;
import com.cardgenerator.CreditEvaluator.application.ex.MicroserviceCommunicationException;
import com.cardgenerator.CreditEvaluator.domain.model.CreditEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-evaluator")
@RequiredArgsConstructor
public class CreditEvaluatorController {

    private final CreditEvaluatorService service;

    @GetMapping
    public String healthCheck() {
        return "ok";
    }

    @GetMapping(value = "/client-status", params = "cpf")
    public ResponseEntity<?> getClientStatus(@RequestParam String cpf) {
        try {
            return ResponseEntity.ok(service.getClientStatus(cpf));
        } catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> evaluateClient(@RequestBody CreditEvaluator data){
        try {
            return ResponseEntity.ok(service.evaluateClient(data.getCpf(), data.getIncome()));
        } catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
