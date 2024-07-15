package com.example.shop.infrastructure.config.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String TRACE_QUEUE = "httpTraceQueue";
    public static final String TRACE_EXCHANGE = "httpTraceExchange";
    public static final String TRACE_ROUTING_KEY = "http.trace.#";

    @Bean
    public Queue traceQueue() {
        return new Queue(TRACE_QUEUE, true);
    }

    @Bean
    public Exchange traceExchange() {
        return new TopicExchange(TRACE_EXCHANGE);
    }

    @Bean
    public Binding traceBinding(Queue traceQueue, Exchange traceExchange) {
        return BindingBuilder.bind(traceQueue).to(traceExchange).with(TRACE_ROUTING_KEY).noargs();
    }
}
