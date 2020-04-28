package com.itheima.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 死信队列配置类
 * @author Steven
 * @version 1.0
 * @description com.itheima.config
 * @date 2020-4-27
 */
@Configuration
public class DlxConfig {
    //创建正常收消息的队列-消息最后再转的正常队列(转发过程由配置完成)
    @Bean
    public Queue queueMessage() {
        return QueueBuilder.durable("queue_message").build();
    }
    //创建死信队列-消息将会先发到此队列
    //可以使用默认的交换机
    @Bean
    public Queue queueDLX() {
        return QueueBuilder.durable("queue_demo_dlx")
                .withArgument("x-message-ttl",10000)  //设置队列过期时间为10秒
                .withArgument("x-dead-letter-exchange","exchange_direct_dlx")  //设置死信交换机
                .withArgument("x-dead-letter-routing-key","item.dlx")  //设置死信路由key
                .withArgument("x-max-length", 1)  //设置队列最大消息条数
                .build();
    }

    //创建死信交换机
    @Bean
    public DirectExchange exchangeDLX() {
        return new DirectExchange("exchange_direct_dlx");
    }

    //正常队列绑定死信交换机
    @Bean
    public Binding bindingDLX(Queue queueMessage,DirectExchange exchangeDLX) {
        return BindingBuilder.bind(queueMessage).to(exchangeDLX).with("item.dlx");
    }
}
