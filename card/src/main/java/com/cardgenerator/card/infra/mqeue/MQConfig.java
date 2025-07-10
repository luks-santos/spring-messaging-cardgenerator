package com.cardgenerator.card.infra.mqeue;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Value("${mq.queues.card-emit}")
    private String cardEmitQueueName;

    @Bean
    public Queue cardEmitQueue() {
        return new Queue(cardEmitQueueName, true);
    }
} 