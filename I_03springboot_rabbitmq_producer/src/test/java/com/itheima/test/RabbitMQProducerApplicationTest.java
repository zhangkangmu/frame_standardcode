package com.itheima.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.test
 * @date 2020-4-26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQProducerApplicationTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMsg() {
        //发送消息
        rabbitTemplate.convertAndSend("topic_exchange_springboot","log.info","这是一条info消息");
        rabbitTemplate.convertAndSend("topic_exchange_springboot","log.error","这是一条error消息");
        rabbitTemplate.convertAndSend("topic_exchange_springboot","log.warning","这是一条warning消息");
        rabbitTemplate.convertAndSend("topic_exchange_springboot","log.info.add","这是一条info.add消息");
        rabbitTemplate.convertAndSend("topic_exchange_springboot","log.info.update","这是一条info.update消息");
    }
}
