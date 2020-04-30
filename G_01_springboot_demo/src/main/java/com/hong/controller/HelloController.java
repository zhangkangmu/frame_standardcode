package com.hong.controller;

import com.hong.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.controller
 * @date 2020-4-21
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    //user.name是springboot内置属性，读取到的值是计算机的名字,所以配置属性的时候不能用user
    //@Value只能获取普通的值,不能获取对象等,要是有对象得用Environment
    //如果是  @Value("${user1}")  由于是对象,会报错
    @Value("${user1.name}")
    private String name;
    //主要在user对象里配置了信息
    @Autowired
    private User user;
    //Environment映射用于一个配置文件中有太多属性名的方便读取方式。
    @Autowired
    private Environment env;

    //没有属性的值需要通过角标获取
    @Value("${userList2[0]}")
    private String userList2;

    @RequestMapping("/hello2")
    public String hello() {
        System.out.println("Hello SpringBoot2!");
        System.out.println("userList2-没有属性的值:"+userList2);
        return "Hello SpringBoot2!name:" + name + ",User:" + user
                + ",env:" + env.getProperty("name") + "-" + env.getProperty("sex")
                + "-" + env.getProperty("user1.name");
    }
}
