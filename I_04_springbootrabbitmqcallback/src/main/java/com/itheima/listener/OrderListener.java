package com.itheima.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 订单消息监听器
 * @author zhangyuhong
 * @description com.itheima.listener
 */
@Component
public class OrderListener {

    //监听正常队列
    @RabbitListener(queues = "queue_order_message")
    public void msg(Message message, Channel channel, String msg) {
        System.out.println(new Date() + "获取到下单消息:" + msg);
        try {
            System.out.println("模拟检查订单有没有支付开始=====start");
            Thread.sleep(1000);
            System.out.println("模拟检查订单有没有支付结束=====end");
            System.out.println("用户没付款，检查没通过，进入回滚库存处理...");
            //签收
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
