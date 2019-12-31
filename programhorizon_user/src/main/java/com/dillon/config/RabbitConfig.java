package com.dillon.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 1:50 PM
 */
@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_B = "my-mq-exchange";
    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    //创建Fanout交换机
    @Bean
    public FanoutExchange createFanoutExchange() {
        return new FanoutExchange(EXCHANGE_B);
    }
    //创建对应Queue
    @Bean
    public Queue queueF1() {
        return new Queue(QUEUE_A, true);
    }
    @Bean
    public Queue queueF2() {
        return new Queue(QUEUE_B, true);
    }
    //绑定交换机和queue
    @Bean
    public Binding bindingF1() {
        return BindingBuilder.bind(queueF1()).to(createFanoutExchange());
    }
    @Bean
    public Binding bindingF2() {
        return BindingBuilder.bind(queueF2()).to(createFanoutExchange());
    }
}
