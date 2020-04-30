package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima
 * @date 2020-4-26
 */
//需要用到这个类,test里会间接运行类
@SpringBootApplication
public class RabbitMQProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQProducerApplication.class, args);
    }

}
