package com.itheima;

import com.itheima.feign.Test2HelloFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 描述
 * @author ljh
 * @packagename com.itheima
 * @version 1.0
 * @date 2020/6/23
 */
@SpringBootApplication
//该注解注册该微服务到注册中心 启用
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.itheima.feign")

public class NacosClient1Application {
    public static void main(String[] args) {
        SpringApplication.run(NacosClient1Application.class, args);
    }

    @RestController
    @RefreshScope//启动刷新配置
    public class TestController {

        @Autowired
        private Test2HelloFeign test2HelloFeign;

        @Value("${info.url:123456}")
        private String url;

        @GetMapping("/hello")
        public String hello() {
            System.out.println("获取到配置中心的值："+url);
            return test2HelloFeign.hello();
        }
    }
}
