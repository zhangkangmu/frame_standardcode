package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima
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

        @GetMapping("hello")
        public String hello() {
            return "Hello Actuator...";
        }
    }
}
