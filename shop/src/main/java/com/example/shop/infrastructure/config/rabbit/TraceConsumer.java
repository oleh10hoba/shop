package com.example.shop.infrastructure.config.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TraceConsumer {

    @RabbitListener(queues = RabbitConfig.TRACE_QUEUE)
    public void receiveTrace(String traceMessage) {
        // Process the trace message
        System.out.println("Received trace: " + traceMessage);
    }
}
