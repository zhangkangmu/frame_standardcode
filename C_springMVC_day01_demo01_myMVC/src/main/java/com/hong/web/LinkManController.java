package com.hong.web;

import com.hong.mymvc1.RequestMapping;
import com.hong.mymvc2.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于写联系人相关的业务方法，相当于以前的LinkManServlet。
 * 只是不需要继承任何父类
 * @author liuyp
 * @date 2020/03/02
 */
@Controller
public class LinkManController {

    @RequestMapping("/linkMan/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("LinkManController.delete：删除联系人");
    }

    @RequestMapping("/linkMan/queryAll")
    public void queryAll(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("LinkManController.queryAll：查询所有联系人");
    }
}
