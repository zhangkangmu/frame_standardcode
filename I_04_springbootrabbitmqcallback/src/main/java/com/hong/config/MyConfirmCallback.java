package com.hong.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * ConfirmCallback回调实现
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.config
 * @date 2020-4-27
 */
@Component
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback {
    /**
     * 实现回调逻辑
     * @param correlationData 关系数据，消息内容
     * @param ack 确认结果 true:消息成功发送到交换机中  false:消息发送到交换机失败
     * @param cause 原因：如果失败，返回一个错误内容，成功,null
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        //如果成功，不用回滚订单信息
        if (ack) {
            System.out.println("消息成功发送到了交换机，流程完成...错误原因:" + cause);
        }else{
            //如果失败，回滚订单信息(删除之前保存订单)
            System.out.println("消息发送到交换机失败，回滚订单...错误原因:" + cause);
        }
    }
}
