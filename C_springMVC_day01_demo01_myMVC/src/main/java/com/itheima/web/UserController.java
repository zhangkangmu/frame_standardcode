package com.itheima.web;

import com.itheima.mymvc1.RequestMapping;
import com.itheima.mymvc2.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 放用户模块的功能。相当于以前的UserServlet
 * @author liuyp
 * @date 2020/03/02
 */
@Controller
public class UserController {

    @RequestMapping("/user/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet.register：注册功能");
    }

    @RequestMapping("/user/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet.login：登录功能");
    }
}
