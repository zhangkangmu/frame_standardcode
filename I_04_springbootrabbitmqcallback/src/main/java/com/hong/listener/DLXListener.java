package com.hong.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 实现死信队列消息监听
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.listener
 * @date 2020-4-27
 */
@Component
public class DLXListener {

    //绑定监听队列---接入签收代码后
    //@RabbitListener(queues = "queue_demo_dlx")
    public void msg(Message message, Channel channel, String msg) {
        try {
            System.out.println("监听到死信消息，开始模拟拒签....内容为：" + msg);
            //拒签消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
