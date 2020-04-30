package com.hong.mq.demo03_fanout;

import com.hong.mq.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * RabbitMQ入门
 * 完成消费者-简单模式
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.mq.simple
 * @date 2020-4-26
 */
public class FanoutConsumer2 {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtils.getConnection();
            //8、创建频道-channel=connection.createChannel()
            Channel channel = connection.createChannel();
            //9、声明队列-channel.queueDeclare(名称，是否持久化，是否独占本连接,是否自动删除,附加参数)
            channel.queueDeclare("fanout_queue2", true, false, false, null);
            //队列绑定交换机-queueBind(队列名，交换机名，路由key[广播消息的路由为空])
            channel.queueBind("fanout_queue2", "fanout_exchange", "");
            //创建消费者
            Consumer callback = new DefaultConsumer(channel){
                /**
                 * @param consumerTag 消费者标签，在channel.basicConsume时候可以指定
                 * @param envelope 信封，消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
                 * @param properties  属性信息(生产者的发送时指定)
                 * @param body 消息内容
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //读取到消息，查看内容
                    long deliveryTag = envelope.getDeliveryTag();  //消息标签-消息id
                    String exchange = envelope.getExchange();  //使用的交换机
                    String routingKey = envelope.getRoutingKey();  //路由key
                    String msg = new String(body, "utf-8");  //消息内容

                    System.out.println(
                            "consumerTag:" + consumerTag +
                            ",routingKey:" + routingKey +
                            ",exchange:" + exchange +
                            ",deliveryTag:" + deliveryTag +
                            ",消息体内容为:" + msg);

                }
            };

            //接收消息
            //channel.basicConsume("simple_queue", callback);
            //basicConsume(队列名，是否自动确认,处理消息的消费者)
            channel.basicConsume("fanout_queue2", true, callback);
            //12、关闭资源-channel.close();connection.close()

            //建议消费消息时，不要关闭连接，我们可一直处于监听消息的状态
            //channel.close();
            //connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
