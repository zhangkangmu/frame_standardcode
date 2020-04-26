package com.itheima.mq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ连接工具抽取
 * @author Steven
 * @version 1.0
 * @description com.itheima.mq.utils
 * @date 2020-4-26
 */
public class ConnectionUtils {

    /**
     * 获取RabbitMQ连接
     * @return
     */
    public static Connection getConnection() {
        try {
            //1、创建链接工厂对象-factory=newConnectionFactory()
            ConnectionFactory factory = new ConnectionFactory();
            //2、设置RabbitMQ服务主机地址，默认localhost-factory.setHost("localhost")
            factory.setHost("localhost");
            //3、设置RabbitMQ服务端口，默认-1-factory.setPort(5672)
            factory.setPort(5672);
            //4、设置虚拟主机名字，默认/-factory.setVirtualHost("szitheima")
            factory.setVirtualHost("szitheima");
            //5、设置用户连接名，默认guest-factory.setUsername("admin")
            factory.setUsername("admin");
            //6、设置链接密码，默认guest-factory.setPassword("admin")
            factory.setPassword("admin");
            //7、创建链接-connection=factory.newConnection()
            Connection connection = factory.newConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
