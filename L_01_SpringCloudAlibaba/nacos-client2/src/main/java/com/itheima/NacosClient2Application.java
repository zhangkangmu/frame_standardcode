package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
@EnableDiscoveryClient
public class NacosClient2Application {
    public static void main(String[] args) {
        SpringApplication.run(NacosClient2Application.class,args);
    }


    @RestController
    public class Test2Controller{

        @GetMapping("/test2hello")
        public String hello(){
            return "i am from 9002";
        }
    }
}
