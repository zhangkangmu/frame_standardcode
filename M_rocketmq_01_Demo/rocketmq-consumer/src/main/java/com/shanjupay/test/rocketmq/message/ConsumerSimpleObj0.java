package com.shanjupay.test.rocketmq.message;

import com.shanjupay.test.rocketmq.model.OrderExt;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 *
 * 直接接收对象(发送的时候还是直接convertAndSend发)
 **/
@Component
@RocketMQMessageListener(topic = "my-topic-obj",consumerGroup="demo-consumer-group-obj")
public class ConsumerSimpleObj0 implements RocketMQListener<OrderExt> {

    @Override
    public void onMessage(OrderExt c) {
        System.out.println(c);
    }
}
