package com.cardgenerator.CreditEvaluator.application.ex;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException() {
        super("Client not found!");
    }
}
