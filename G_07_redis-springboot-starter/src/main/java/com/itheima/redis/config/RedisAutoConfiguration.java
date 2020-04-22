package com.itheima.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.redis.config
 * @date 2020-4-22
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
//如果项目中有Jedis依赖，我们才启动以下的配置
@ConditionalOnClass(Jedis.class)
public class RedisAutoConfiguration {
    //@Autowired
    //private RedisProperties redisProperties;

    @Bean
    //当我们容器中没有Jedis对象时才执行以下配置
    @ConditionalOnMissingBean(Jedis.class)
    public Jedis jedis(RedisProperties redisProperties) {
        System.out.println("SpringBoot自动创建Jedis对象,host:" + redisProperties.getHost() + ",port:"
                + redisProperties.getPort());
        //return new Jedis("localhost", 6379);
        return new Jedis(redisProperties.getHost(), redisProperties.getPort());
    }
}
