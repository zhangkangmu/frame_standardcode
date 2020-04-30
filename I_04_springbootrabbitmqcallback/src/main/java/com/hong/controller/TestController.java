package com.hong.controller;

import com.hong.config.MyConfirmCallback;
import com.hong.config.MyReturnCallback;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.controller
 * @date 2020-4-27
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MyConfirmCallback myConfirmCallback;
    @Autowired
    private MyReturnCallback myReturnCallback;

    @RequestMapping("send1")
    public String send1() {
        //发送消息之前指定ConfirmCallback
        rabbitTemplate.setConfirmCallback(myConfirmCallback);
        //发送消息
        rabbitTemplate.convertAndSend("exchange_direct_callback01", "item.inster", "订单下单成功，开始发送扣减库存消息...");
        return "ok";
    }

    @RequestMapping("send2")
    public String send2() {
        //发送消息之前指定ConfirmCallback
        rabbitTemplate.setConfirmCallback(myConfirmCallback);

        //发送消息前设置ReturnCallback
        rabbitTemplate.setReturnCallback(myReturnCallback);

        //发送消息
        //rabbitTemplate.convertAndSend("exchange_direct_callback01", "item.inster", "订单下单成功，开始发送扣减库存消息key...");

        //测试发送多条消息
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend("exchange_direct_callback01", "item.inster", "订单下单成功，开始发送扣减库存消息key..." + i);
        }
        return "ok";
    }


    @RequestMapping("sendTTL")
    public String sendTTL() {
        rabbitTemplate.convertAndSend("exchange_direct_ttl", "item.ttl", "过期消息Testing...");
        return "ok";
    }

    @RequestMapping("sendDLX")
    public String sendDLX() {
        rabbitTemplate.convertAndSend("queue_demo_dlx", "hello dlx...");
        return "ok";
    }

    @RequestMapping("sendOrder")
    public String sendOrder() {
        //发送消息到死信队列   可以使用默认的交换机 指定ourtingkey为死信队列名即可
        System.out.println(new Date() + "，用户下单成功，10秒钟之后如果没有支付，则过期，回滚订单");

        //rabbitTemplate.convertAndSend("queue_order_delay", "hello delay...");

        rabbitTemplate.convertAndSend("queue_order_delay", (Object) "下单成功，等待检查支付...", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");//设置该消息的过期时间
                return message;
            }
        });
        return "用户下单成功，10秒钟之后如果没有支付，则过期，回滚订单";
    }
}
