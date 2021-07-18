package com.shanjupay.test.rocketmq.message;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 *
 * 测试重试消费
 **/
@Component
@RocketMQMessageListener(topic = "my-topic-obj",consumerGroup="demo-consumer-group-obj")
//MessageExt这个对象里存储了重试的此时
public class ConsumerSimpleObj implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        String jsonString = new String(body);

        System.out.println(jsonString);
        //重试次数
        int reconsumeTimes = messageExt.getReconsumeTimes();
        if(reconsumeTimes>2){  //重试次数大于2
            //将此消息加入数据库，由单独的程序或人工来处理(正常应该这样处理,后面那个是默认的)
            //....
        }
        if(1==1){
        	//主动抛出异常,让他失败,他会自动重试,重试16个等级,都失败的话,就进入死信队列(默认)
            throw new RuntimeException("处理消息失败！！");
        }

    }
}
