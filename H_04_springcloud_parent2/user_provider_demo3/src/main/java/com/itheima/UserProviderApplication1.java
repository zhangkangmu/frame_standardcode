package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient  //开启Eureka客户端
@EnableDiscoveryClient  //开启客户端-支持euraka、zookeeper...
public class UserProviderApplication1 {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication1.class,args);
    }
}
