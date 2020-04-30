package com.hong.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 入门SpringBoot集成RabbitMQ消费者
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.listener
 * @date 2020-4-26
 */
@Component
public class MessageListener {

    /**
     * 实现RabbitMQ监听器
     * @param msg 接收到的消息内容
     */
    //告诉SpringBoot我们以下方法，监听个队列
    @RabbitListener(queues = "topic_queue_springboot")
    public void topicListener(String msg) {
        System.out.println("收到SpringBoot整合RabbitMQ的消息：" + msg);
    }
}
