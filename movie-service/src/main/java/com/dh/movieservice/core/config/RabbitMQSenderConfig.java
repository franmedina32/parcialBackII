package com.dh.movieservice.core.config;

import org.springframework.amqp.core.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {

    @Value("${exchange.movie.name}")
    private String movieExchange;

    @Value("${queue.movie.name}")
    private String movieQueue;

    @Bean
    public Queue movieQueue() {
        return new Queue(movieQueue, true);
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(movieExchange);
    }

    @Bean
    public Binding bindingTopic(TopicExchange topic,
                             Queue movieQueue) {
        return BindingBuilder.bind(movieQueue)
                .to(topic)
                .with("*.orange.*");
    }




}
