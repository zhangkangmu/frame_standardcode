package com.hong.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * ReturnCallback回调实现
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.config
 * @date 2020-4-27
 */
@Component
public class MyReturnCallback implements RabbitTemplate.ReturnCallback {
    /**
     * 只要进入此方法，那么一定是发送异常
     * @param message 发送消息本身对象
     * @param replyCode 错误状态码
     * @param replyText 错误消息内容
     * @param exchange 交换机
     * @param routingKey 路由key
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("----------------------------------------------------");
        System.out.println("消息从交换机转到队列时发生了异常...内容如下：");
        System.out.println("消息体内容：" + new String(message.getBody()));
        System.out.println("错误代码：" + replyCode);
        System.out.println("错误消息：" + replyText);
        System.out.println("交换机：" + exchange);
        System.out.println("路由key：" + routingKey);
        System.out.println("开始回滚订单信息.....");
        System.out.println("----------------------------------------------------");
    }
}
