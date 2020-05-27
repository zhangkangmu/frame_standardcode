package com.hong.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 实现死信队列消息监听
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.listener
 * @date 2020-4-27
 */
@Component
public class DLXListener {

   /*
    第一种：签收 可批量处理
    channel.basicAck()
    第二种：拒绝签收 可批量处理
    channel.basicNack()
    第三种：拒绝签收 不可批量处理
    channel.basicReject()*/

    //绑定监听队列---接入签收代码后
    //@RabbitListener(queues = "queue_demo_dlx")
    public void msg(Message message, Channel channel, String msg) {
        try {
            System.out.println("监听到死信消息，开始模拟拒签....内容为：" + msg);
            //拒签消息 getDeliveryTag:表示一个队列的tag标志,每次出了一个就+1  不批量,不放回消息队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
