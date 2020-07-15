package io.microservice.SpringbootRabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootRabbitMqApplication {

	public static final String EXCHANGE_NAME="r_msg";
	public static final String DEFAULT_PARSING_QUEUE="default_mq";
	public static final String ROUTING_KEY="abcd1234";

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitMqApplication.class, args);
	}

	@Bean
	public TopicExchange tipsExchange(){
		return new TopicExchange(EXCHANGE_NAME);
	}
	@Bean
	public Queue defaultParsingQueue(){
		return new Queue(DEFAULT_PARSING_QUEUE);
	}
	@Bean
	public Binding queueToExchangeBinding(){
		return BindingBuilder.bind(defaultParsingQueue()).to(tipsExchange()).with(ROUTING_KEY);
	}
}
