package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/19 9:58
 * @Version V1.0
 */
@Controller  // 该注解表示该类控制器
@RequestMapping(value = "/user") // 一级路径
public class UserController {

    @RequestMapping(value = "/testInterceptor")
    public String testInterceptor(){
        System.out.println("欢迎访问UserController类中的testInterceptor的方法！");
        return "success";
    }
}
