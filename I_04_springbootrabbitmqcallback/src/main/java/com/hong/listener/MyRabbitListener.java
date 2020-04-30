package com.hong.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 实现消息监听
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.listener
 * @date 2020-4-27
 */
@Component
public class MyRabbitListener {

    /*//绑定监听队列--没有签收代码前
    @RabbitListener(queues = "queue_callback01")
    public void msg(String msg) {
        System.out.println("接收到下单成功消息，准备完扣减库存操作..." + "内容为：" + msg);

        //手动签收...
    }*/

    //绑定监听队列---接入签收代码后
    @RabbitListener(queues = "queue_callback01")
    public void msg(Message message, Channel channel, String msg) {
        System.out.println("---------------------------------------------");
        try {
            System.out.println("接收到下单成功消息，准备完扣减库存操作..." + "内容为：" + msg);
            System.out.println("执行扣减库存操作.....");

            Thread.sleep(1000);

            //int i = 1 / 0;
            //业务没有发生异常，手动签收...
            //basicAck(消息标签，是否批量[一般情况都是false])
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            e.printStackTrace();

            try {
                //拒签消息：1.拒签后，直接丢弃消息；2.拒签后，重回队列；
                //1.拒签后，直接丢弃消息
                //basicAck(消息标签，是否批量[一般情况都是false],是否重回队列)
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                //System.out.println("业务发生了异常，拒签并直接丢弃消息....");

                // 2.拒签后，重回队列；
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                //System.out.println("业务发生了异常，拒签并让消息重新回到队列中....");

                //3.拒签消息-channel.basicReject()  不支持批量处理
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
                System.out.println("业务发生了异常，basicReject拒签并直接丢弃消息....");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("---------------------------------------------");
    }
}
