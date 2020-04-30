package com.hong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima
 * @date 2020-4-23
 */
@SpringBootApplication
public class Day1ResttemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(Day1ResttemplateApplication.class, args);
    }

    //创建注入一个RestTemplate
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
