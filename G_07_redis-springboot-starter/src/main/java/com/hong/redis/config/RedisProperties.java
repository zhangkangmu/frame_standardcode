package com.hong.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.redis.config
 * @date 2020-4-22
 */
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private String host = "localhost";  //设置redis连接默认值
    private Integer port = 6379;  //默认端口

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
