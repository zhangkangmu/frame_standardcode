package com.hong.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/22 14:51
 * @Version V1.0
 */
@Controller
@RequestMapping(value = "/index")
public class WelcomeController {


    // 跳转到/WEB-INF/page/index.jsp
    @RequestMapping(value = "/welcome")
    public String welcome(){
        // 加载菜单的数据
        // 加载首页显示的数据
        return "index";
    }

    // 跳转到/WEB-INF/page/home.jsp 遇到jsp的跳转的时候404，使用Controller作为中转跳转到对应的jsp即可
    @RequestMapping(value = "/home")
    public String home(){
        // 加载菜单的数据
        // 加载首页显示的数据
        return "home";
    }
}
