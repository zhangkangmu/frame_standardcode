package com.hong.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TTL过期消息配置
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.config
 * @date 2020-4-27
 */
@Configuration
public class TtlConfig {

    //创建队列  -与普通队列不同的地方，这里多设置一个过期时间
    @Bean
    public Queue queueTTL() {
        return QueueBuilder.durable("queue_demo_ttl")
                //withArgument()  --设置队列参数
                //x-message-ttl:表示设置过期时间(毫秒)
                .withArgument("x-message-ttl",10000)
                .build();
    }

    //创建交换机
    @Bean
    public DirectExchange exchangeTTL() {
        return new DirectExchange("exchange_direct_ttl");
    }

    //队列绑定交换机
    @Bean
    public Binding bindingTTL(Queue queueTTL,DirectExchange exchangeTTL) {
        return BindingBuilder.bind(queueTTL).to(exchangeTTL).with("item.ttl");
    }
}
