package io.microservice.SpringbootRabbitMQ.model;

import io.microservice.SpringbootRabbitMQ.SpringbootRabbitMqApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageSender {

    private static final Logger log= LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private final RabbitTemplate rabbitTemplate;


    public MessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000L)
    public void sendPracticalTip(){
        Message message =new Message("New Message Received on "+new Date(),1 ,false);
        rabbitTemplate.convertAndSend(SpringbootRabbitMqApplication.EXCHANGE_NAME,SpringbootRabbitMqApplication.ROUTING_KEY, message);
        log.info("Message sent..{}", message.toString());
    }
}
