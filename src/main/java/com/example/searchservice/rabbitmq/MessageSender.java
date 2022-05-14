package com.example.searchservice.rabbitmq;

import com.example.searchservice.SearchServiceApplication;
import com.example.searchservice.document.Post;
import com.example.searchservice.document.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class MessageSender {

    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

    private final RabbitTemplate rabbitTemplate;

    public MessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 3000000000L)
    public void sendMessage() {
        final Post PostMessage = new Post(203L ,"userId", "Sending message to receiver test");
        final User UserMessage = new User(303L ,"userName", "firstName", "lastName", "email@test");
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(SearchServiceApplication.EXCHANGE_POST, SearchServiceApplication.ROUTING_KEY_POST, PostMessage);
        rabbitTemplate.convertAndSend(SearchServiceApplication.EXCHANGE_USER, SearchServiceApplication.ROUTING_KEY_USER, UserMessage);
    }
}
