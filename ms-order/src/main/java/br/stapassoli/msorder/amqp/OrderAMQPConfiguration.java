package br.stapassoli.msorder.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderAMQPConfiguration {

    @Bean
    public Queue createQueueOrder(){
        return QueueBuilder.nonDurable("order-queue").build();
    }

    @Bean
    public RabbitAdmin configureRabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public ApplicationListener<ApplicationEvent> configureListner(RabbitAdmin admin){
        return event -> admin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter configureConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate configureToUserConverter(ConnectionFactory factory, Jackson2JsonMessageConverter converter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public FanoutExchange createExchange(){
        return ExchangeBuilder.fanoutExchange("payment.ex").build();
    }

    //make bind
    @Bean
    public Binding makeBind(FanoutExchange fanoutExchange){
        return BindingBuilder.bind(createQueueOrder()).to(fanoutExchange);
    }
}
