package com.hong.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangyuhong
 * Date:2020/5/21
 */
@Component
@RabbitListener(queues = "queue.order")
public class PayOrderUpdateListener {


    @RabbitHandler//方法用处理监听到queue.order队列的消息  可靠性消息
    public void handler(String msg) {
      //业务处理
    }
}
