package com.itheima;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima
 * @date 2020-4-27
 */
@SpringBootApplication
public class RabbitMQCallBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQCallBackApplication.class, args);
    }

    //创建队列
    @Bean
    public Queue backQueue() {
        return new Queue("queue_callback01",true);
    }

    //创建交换机
    @Bean
    public DirectExchange backExchange() {
        return new DirectExchange("exchange_direct_callback01");
    }

    //队列绑定交换机
    @Bean
    public Binding backBinding(Queue backQueue,DirectExchange backExchange) {
        return BindingBuilder.bind(backQueue).to(backExchange).with("item.inster");
    }
}
