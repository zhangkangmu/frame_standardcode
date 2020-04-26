package com.itheima.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置RabbitMQ相关信息
 * 队列名、交换机、队列绑定交换机
 * 演示通配符案例
 * @author Steven
 * @version 1.0
 * @description com.itheima.config
 * @date 2020-4-26
 */
@Configuration
public class SendConfig {

    //声明队列
    @Bean
    public Queue topicQueueSpringBoot() {
        //QueueBuilder.durable("topic_queue_springboot").build();
        return new Queue("topic_queue_springboot", true);
    }

    //声明交换机
    @Bean
    public TopicExchange topicExchangeSpringBoot() {
        return new TopicExchange("topic_exchange_springboot");
    }

    //队列绑定交换机
    @Bean
    public Binding queueBindExchange(@Qualifier("topicQueueSpringBoot")Queue queue,
                                     @Qualifier("topicExchangeSpringBoot")TopicExchange exchange) {
        //把队列绑定到交换机上
        return BindingBuilder.bind(queue).to(exchange).with("log.#");
    }
}
