package io.microservice.SpringbootRabbitMQ.model;

import io.microservice.SpringbootRabbitMQ.SpringbootRabbitMqApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private static final Logger log= LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = SpringbootRabbitMqApplication.DEFAULT_PARSING_QUEUE)
    public void consumerDefaultMessage(final Message message){
        log.info("Message Consumed..{}",message.toString());
    }
}
