package com.itheima.baseservlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 核心控制器：BaseServlet
 *      把 和业务无关的基础（核心）逻辑，提取到了父类BaseServlet里
 *
 * 业务控制器：UserServlet、LinkManServlet
 *      把 业务逻辑功能相关的代码，还放到每个Servlet类里。让这个类继承BaseServlet
 *
 * 但是：
 *      1. 业务控制器 每个模块的类（UserServlet、LinkManServlet）必须要继承BaseServlet：耦合性强
 *      2. 客户端无论什么功能，发请求时，都必须要携带一个参数action，指定方法名称
 *
 * 理想状态：
 *      业务控制器是普通Java类，不需要继承任何父类
 *      客户端请求时，不需要再额外传参action
 *
 * 客户端请求：请求到核心控制器，核心控制器要调用业务控制器
 *
 * @author liuyp
 * @date 2020/03/02
 */
@WebServlet(urlPatterns="/linkMan")
public class LinkManServlet extends BaseServlet {
    public void delete(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("LinkManServlet.delete方法：删除联系人");
    }

    public void queryAll(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("LinkManServlet.queryAll：查询所有联系人");
    }
}