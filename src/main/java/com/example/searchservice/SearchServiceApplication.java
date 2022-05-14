package com.example.searchservice;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SearchServiceApplication {

    public static final String EXCHANGE_POST = "exchangePost";
    public static final String EXCHANGE_USER = "exchangeUser";
    public static final String QUEUE_POST = "queuePost";
    public static final String QUEUE_USER= "queueUser";
    public static final String ROUTING_KEY_POST = "routingKeyPost";
    public static final String ROUTING_KEY_USER = "routingKeyUser";

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }

    @Bean
    public TopicExchange PostExchange() {
        return new TopicExchange(EXCHANGE_POST);
    }
    @Bean
    public TopicExchange UserExchange() {
        return new TopicExchange(EXCHANGE_USER);
    }

    @Bean
    public Queue PostQueue() {
        return new Queue(QUEUE_POST);
    }
    @Bean
    public Queue UserQueue() {
        return new Queue(QUEUE_USER);
    }

    @Bean
    public Binding declareBindingPost() {
        return BindingBuilder.bind(PostQueue()).to(PostExchange()).with(ROUTING_KEY_POST);
    }

    @Bean
    public Binding declareBindingUser() {
        return BindingBuilder.bind(UserQueue()).to(UserExchange()).with(ROUTING_KEY_USER);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
