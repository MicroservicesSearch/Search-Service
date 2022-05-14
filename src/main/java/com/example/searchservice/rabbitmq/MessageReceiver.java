package com.example.searchservice.rabbitmq;

import com.example.searchservice.SearchServiceApplication;
import com.example.searchservice.controller.PostController;
import com.example.searchservice.controller.UserController;
import com.example.searchservice.document.Post;
import com.example.searchservice.document.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {
    PostController postController;
    UserController userController;

    public MessageReceiver(PostController postController, UserController userController) {
        this.postController = postController;
        this.userController = userController;
    }

    private static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    @RabbitListener(queues = SearchServiceApplication.QUEUE_POST)
    public void receiveMessagePost(final Post message) {
        log.info("Received message from POST: {}", message.toString());
        postController.save(message);
    }
    @RabbitListener(queues = SearchServiceApplication.QUEUE_USER)
    public void receiveMessageUser(final User message) {
        log.info("Received message from USER: {}", message.toString());
        userController.save(message);
    }
}
