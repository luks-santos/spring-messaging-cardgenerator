package com.cardgenerator.client.application.representation;

import com.cardgenerator.client.domain.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientSaveRequest {
    private String name;
    private String cpf;
    private Integer age;

    public Client toClient() {
        return new Client(name, cpf, age);
    }
}
