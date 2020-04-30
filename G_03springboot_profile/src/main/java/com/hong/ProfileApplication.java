package com.hong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong
 * @date 2020-4-21
 */
@SpringBootApplication
public class ProfileApplication {
    public static void main(String[] args) {
       //如果不想配置这么多的环境yml,可以在jvm虚拟机参数配置 -Dspring.profiles.active=dev
        SpringApplication.run(ProfileApplication.class, args);
    }
}
