package com.hong.mq.demo01_simple;

import com.hong.mq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * RabbitMQ入门案例-生产者
 * 演示发送简单的MQ消息
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.mq.simple
 * @date 2020-4-26
 */
public class SimpleProducer {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            //8、创建频道-channel=connection.createChannel()
            Channel channel = connection.createChannel();
            //9、声明队列-channel.queueDeclare(队列名称，是否持久化，是否独占本连接,是否自动删除,附加参数)
            channel.queueDeclare("simple_queue", true, false, false, null);
            //10、创建消息-Stringm=xxx
            String msg = "hello,这是我们第2次发送MQ消息";
            //11、消息发送-channel.basicPublish(交换机[默认DefaultExchage],路由key[简单模式可以传递队列名称],消息其它属性,消息内容)
            channel.basicPublish("", "simple_queue", null, msg.getBytes("utf-8"));
            //12、关闭资源-channel.close();connection.close()
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
