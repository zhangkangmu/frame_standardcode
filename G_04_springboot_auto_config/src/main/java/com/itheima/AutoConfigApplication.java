package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima
 * @date 2020-4-22
 */
@SpringBootApplication
public class AutoConfigApplication {
    public static void main(String[] args) {
        //new ClasspathXml
        //接收Spring容器上下文对象
        ConfigurableApplicationContext context = SpringApplication.run(AutoConfigApplication.class, args);
        //获取容器中的bean对象
        //Object obj = context.getBean("redisTemplate");
        //System.out.println(redisTemplate);

        Object obj = context.getBean("user");
        System.out.println(obj);
    }
}
