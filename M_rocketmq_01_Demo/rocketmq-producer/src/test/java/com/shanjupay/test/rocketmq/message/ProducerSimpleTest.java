package com.shanjupay.test.rocketmq.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shanjupay.test.rocketmq.model.OrderExt;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerSimpleTest {

    @Autowired
    ProducerSimple producerSimple;

    /**
     * 发送同步消息
     */
    @Test
    public void testSendSyncMsg(){
        producerSimple.sendSyncMsg("my-topic","第2条同步消息");
    }

    /**
     * 发送异步消息
     */
    @Test
    public void testSendASyncMsg(){
        producerSimple.sendASyncMsg("my-topic","第1条异步消息");
        try {
            //防止进程结束,消费者接收消息后异步回调看是否成功,但是这里又结束了,所以要让他等待
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //同步发送对象消息,自动转成json
    @Test
    public void testSendMsgByJson(){
        OrderExt orderExt = new OrderExt();
        orderExt.setId("1122");
        orderExt.setMoney(893L);
        orderExt.setCreateTime(new Date());

        producerSimple.sendMsgByJson("my-topic-obj",orderExt);
    }

    //发送延迟消息, 依靠等级,时间不是自己固定的
    @Test
    public void testSendMsgByJsonDelay(){
        OrderExt orderExt = new OrderExt();
        orderExt.setId("55555555");
        orderExt.setMoney(1111L);
        orderExt.setCreateTime(new Date());

        producerSimple.sendMsgByJsonDelay("my-topic-obj",orderExt);
    }

    //测试发送异步消息
    @Test
    public void testSendAsyncMsgByJsonDelay() throws JsonProcessingException, InterruptedException,
            RemotingException, MQClientException, MQBrokerException {
        OrderExt orderExt = new OrderExt();
        orderExt.setId(UUID.randomUUID().toString());
        orderExt.setCreateTime(new Date());
        orderExt.setMoney(168L);
        orderExt.setTitle("测试订单");
        this.producerSimple.sendAsyncMsgByJsonDelay("my‐topic‐obj"
                ,orderExt);
        System.out.println("end...");
                Thread.sleep(20000);
    }
}
