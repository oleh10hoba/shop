package com.example.shop.infrastructure.config.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TraceService {

    private final RabbitTemplate rabbitTemplate;

    public TraceService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTrace(String traceMessage) {
        rabbitTemplate.convertAndSend(RabbitConfig.TRACE_EXCHANGE, "http.trace.log", traceMessage);
    }
}
