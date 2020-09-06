package com.hong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong
 * @date 2020-4-22
 */
@SpringBootApplication
public class ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    @RestController
    @RequestMapping("test")
    class TestController{
        //指定返回格式
//        @GetMapping(value = "/user",produces = {"application/json;charset=UTF-8"})
        @GetMapping("hello")
        public String hello() {
            return "Hello Actuator...";
        }
    }
}
