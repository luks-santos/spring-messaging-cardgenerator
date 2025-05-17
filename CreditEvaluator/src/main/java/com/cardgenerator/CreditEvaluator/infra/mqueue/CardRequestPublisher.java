package com.cardgenerator.CreditEvaluator.infra.mqueue;

import com.cardgenerator.CreditEvaluator.domain.model.CardRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardRequestPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void requestCard(CardRequest data) throws JsonProcessingException {
        String json = convertToJson(data);
        rabbitTemplate.convertAndSend(queue.getName(), json);
    }

    private String convertToJson(CardRequest data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
}
