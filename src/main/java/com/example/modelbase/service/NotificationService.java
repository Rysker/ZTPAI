package com.example.modelbase.service;

import com.example.modelbase.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService
{
    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;
    private final UserService userService;

    public void sendNotification(String message, String username) throws Exception
    {
        String queueName = "userQueue." + username;

        Queue queue = new Queue(queueName, false);
        amqpAdmin.declareQueue(queue);

        rabbitTemplate.convertAndSend(queueName, message);
    }

    public String getNotification(String token) throws Exception
    {
        User user = userService.getUserFromToken(token);
        String queueName = "userQueue." + user.getUsername();
        Object message = rabbitTemplate.receiveAndConvert(queueName);
        return message != null ? message.toString() : null;
    }

}
