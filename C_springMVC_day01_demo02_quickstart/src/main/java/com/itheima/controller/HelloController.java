package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class HelloController {



    // 返回ModelAndView（模型和视图的对象）（Model用来封装响应的数据，View用来指定视图解析器）
//    @RequestMapping(value = "/hello") // 二级路径，该注解表示处理请求的url，让请求找到sayHello的方法
//    public ModelAndView sayHello(){
//        System.out.println("欢迎访问HelloController类中的sayHello的方法！");
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("success");
//        return mv; // 如果在springmvc中配置视图解析器，可以让返回值String执行视图解析器
//    }

    // 返回success
    @RequestMapping(value = "/hello") // 该注解表示处理请求的url，让请求找到sayHello的方法
    public String sayHello(){
        System.out.println("欢迎访问HelloController类中的sayHello的方法！");
        return "success"; // 如果在springmvc中配置视图解析器，可以让返回值String执行视图解析器
    }

    // 返回ModelAndView（模型和视图的对象）（Model用来封装响应的数据，View用来指定视图解析器）

    /**
     * 属性：
         value/path（重点）：用于指定请求的URL。它和path属性的作用是一样的。
         method（重点）：用于指定请求的方式，可用在restful请求方式中。
         params：用于指定限制请求参数的条件。它支持简单的表达式。要求请求参数的key和value必须和配置的一模一样。
             例如：
             params = {"accountName"}，表示请求参数必须有accountName
             params = {"accountName=zhangsan"}，表示请求参数中accountName的值必须是zhangsan。
             params = {"accountName!zhangsan"}，表示请求参数中accountName的值不能是zhangsan。
     headers：用于指定限制请求消息头的条件。
     * @return
     */
    @RequestMapping(value = "/testRequestMapping",method = RequestMethod.GET,params = {"accountName=zhangsan"},headers = "Accept") // 二级路径，该注解表示处理请求的url，让请求找到sayHello的方法
    public String testRequestMapping(){
        System.out.println("欢迎访问HelloController类中的testRequestMapping的方法！");
        return "success"; // 如果在springmvc中配置视图解析器，可以让返回值String执行视图解析器
    }
}
