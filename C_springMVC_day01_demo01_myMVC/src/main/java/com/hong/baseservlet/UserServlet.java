package com.hong.baseservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 每个Servlet里，都有重复的doGet和doPost方法
 *      这些重复的方法，跟业务需求没有关系。
 *
 *      是一些公共的、基础的核心逻辑：根据客户端的请求，调用对应的方法
 *
 * 要把多个类里共同的方法提取到一个父类里，子类继承父类即可。
 * idea的快捷方式：ctrl + shift + alt + t，提取重构
 *
 * @author liuyp
 * @date 2020/03/02
 */
@WebServlet(urlPatterns="/user")
public class UserServlet extends BaseServlet {
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet.register：注册功能");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet.login：登录功能");
    }
}
