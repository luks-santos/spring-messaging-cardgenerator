package com.cardgenerator.CreditEvaluator.application.ex;

import lombok.Getter;

@Getter
public class MicroserviceCommunicationException extends RuntimeException {

  private final Integer status;

  public MicroserviceCommunicationException(String message, Integer status) {
    super(message);
    this.status = status;
  }
}
