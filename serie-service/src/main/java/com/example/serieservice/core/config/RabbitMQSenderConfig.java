package com.example.serieservice.core.config;

import org.springframework.amqp.core.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {

    @Value("${exchange.serie.name}")
    private String serieExchange;

    @Value("${queue.serie.name}")
    private String serieQueue;

    @Bean
    public Queue serieQueue() {
        return new Queue(serieQueue, true);
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(serieExchange);
    }

    @Bean
    public Binding bindingTopic(TopicExchange topic,
                             Queue serieQueue) {
        return BindingBuilder.bind(serieQueue)
                .to(topic)
                .with("*.orange.*");
    }



}
