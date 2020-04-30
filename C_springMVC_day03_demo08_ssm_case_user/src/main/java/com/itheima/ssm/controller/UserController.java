package com.itheima.ssm.controller;

import com.itheima.ssm.domain.User;
import com.itheima.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/22 14:51
 * @Version V1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    // 注册保存
    /**
     * 业务：保存成功，跳转到login.jsp
     *       保存失败，跳转到register.jsp
     * @param user
     * @return
     */
    @RequestMapping(value = "/save")
    public String save(User user){
        int row = userService.save(user);
        // 保存成功，跳转到login.jsp
        if(row>0){
            return "redirect:/login.jsp";
        }
        // 保存失败，跳转到register.jsp
        else{
            return "redirect:/register.jsp";
        }
    }

    // 登录
    @RequestMapping(value = "/login")
    public String login(User user, HttpSession session){
        User u = userService.findUserByNumberAndPassword(user);
        // 登录成功，将登录信息存放到Session中，跳转到/WEB-INF/page/index.jsp
        if(u!=null){
            session.setAttribute("globle_user",u);
            // return "index"; 通过跳转到WelcomeController类中的welcome方法，跳转到/WEB-INF/page/index.jsp
            return "redirect:/index/welcome";
        }
        // 登录成功，跳转到login.jsp
        else{
            return "redirect:/login.jsp";
        }
    }
}
