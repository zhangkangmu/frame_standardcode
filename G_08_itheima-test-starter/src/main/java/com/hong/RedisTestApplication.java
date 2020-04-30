package com.hong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima
 * @date 2020-4-22
 */
@SpringBootApplication
public class RedisTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RedisTestApplication.class, args);
        Jedis jedis = context.getBean(Jedis.class);
        System.out.println(jedis);
    }

    //使用者自己创建了一个Jedis
    //启动器就不再需要再创建对象
    /*@Bean
    public Jedis jedis() {
        return new Jedis("localhost",7777);
    }*/
}
