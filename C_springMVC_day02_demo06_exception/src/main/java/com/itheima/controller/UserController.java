package com.itheima.controller;

import com.itheima.exception.SysException;
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

    @RequestMapping(value = "/testException")
    public String testException() throws SysException {
        try {
            System.out.println("欢迎访问UserController类中的testException的方法！");
            int i = 10/0;
        } catch (Exception e) {
            e.printStackTrace(); // 异常信息，放任不管，打印控制台（调试）
            throw new SysException("当前网络不给力，请稍后再试...");
        }
        return "success";
    }
}
