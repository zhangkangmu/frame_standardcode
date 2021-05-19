package com.itheima;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.itheima.feign.SentinelClient1Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 描述
 * @author ljh
 * @packagename com.itheima
 * @version 1.0
 * @date 2020/6/24
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.itheima.feign")
public class SentinelClient2Application {
    public static void main(String[] args) {
        SpringApplication.run(SentinelClient2Application.class, args);
    }

    @RestController
    public class TestController {
        @Autowired
        private SentinelClient1Feign sentinelClient1Feign;

        //流控  QPS
        @GetMapping("/hellofeign")
        @SentinelResource(value="testA")
        public String hellofeign() {
            return sentinelClient1Feign.hello12();
        }



    }
}
