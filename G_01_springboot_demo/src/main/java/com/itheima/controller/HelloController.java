package com.itheima.controller;

import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.controller
 * @date 2020-4-21
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    //user.name是springboot内置属性，读取到的值是计算机的名字,所以配置属性的时候不能用user
    //@Value只能获取普通的值,不能获取对象等,要是有对象得用Environment
    @Value("${user1.name}")
    private String name;
    @Autowired
    private User user;
    @Autowired
    private Environment env;

    @RequestMapping("/hello2")
    public String hello() {
        System.out.println("Hello SpringBoot2!");
        return "Hello SpringBoot2!name:" + name + ",User:" + user
                + ",env:" + env.getProperty("name") + "-" + env.getProperty("sex")
                + "-" + env.getProperty("user1.name");
    }
}
