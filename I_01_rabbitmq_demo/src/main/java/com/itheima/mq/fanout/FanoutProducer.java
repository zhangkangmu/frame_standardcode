package com.itheima.mq.fanout;

import com.itheima.mq.utils.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * RabbitMQ入门案例-生产者
 * 演示发送简单的MQ消息
 * @author Steven
 * @version 1.0
 * @description com.itheima.mq.simple
 * @date 2020-4-26
 */
public class FanoutProducer {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            //8、创建频道-channel=connection.createChannel()
            Channel channel = connection.createChannel();
            //9、声明队列-channel.queueDeclare(名称，是否持久化，是否独占本连接,是否自动删除,附加参数)
            //channel.queueDeclare("work_queue", true, false, false, null);
            //声明交换机-exchangeDeclare(交换机名称，交换机类型[3])
            //广播交换机
            channel.exchangeDeclare("fanout_exchange", BuiltinExchangeType.FANOUT);
            for (int i = 0; i < 10; i++) {
                //10、创建消息-Stringm=xxx
                String msg = "hello,这是我们第" + i + "次发送FanoutQueue-MQ消息";
                //11、消息发送-channel.basicPublish(交换机[默认DefaultExchage],路由key[简单模式可以传递队列名称],消息其它属性,消息内容)
                channel.basicPublish("fanout_exchange", "", null, msg.getBytes("utf-8"));
            }
            //12、关闭资源-channel.close();connection.close()
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
