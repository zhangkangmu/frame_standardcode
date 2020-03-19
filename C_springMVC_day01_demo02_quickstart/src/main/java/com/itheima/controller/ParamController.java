package com.itheima.controller;

import com.itheima.domain.Account;
import com.itheima.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/19 9:58
 * @Version V1.0
 */
@Controller  // 该注解表示该类控制器
@RequestMapping(value = "/param") // 一级路径
public class ParamController {

    /**3.5.2 基本数据类型和字符串类型
     *  参数绑定，将请求的参数名称，放置到方法中的参数上即可，要求请求的参数名称和方法参数的名称相同
     *  类型转换：将String类型转换成Int类型
     * */
    @RequestMapping(value = "/testParam")
    public String testParam(String username,Integer age){
        System.out.println("欢迎执行ParamController类中的testParam方法！username:"+username+"   age:"+age);
        return "success";
    }

    /**
     * 3.5.3 实体类型（JavaBean）  【案例一】：封装User
     * 表单传递的参数名称，和实体类中的属性名称要一致
     */
    @RequestMapping(value = "/testParam2")
    public String testParam2(User user){
        System.out.println("欢迎执行ParamController类中的testParam方法！user:"+user);
        return "success";
    }

    /**
     * 3.5.3 实体类型（JavaBean）  【案例二】：封装Account，Account实体中有User对象。
     * 表单传递的参数名称，和实体类中的属性名称要一致
     * 3.5.4 Post请求参数中文乱码的解决
     * 3.5.5 给集合属性数据封装（了解）
     */
    @RequestMapping(value = "/saveAccount")
    public String saveAccount(Account account){
        System.out.println("欢迎执行ParamController类中的saveAccount方法！account:"+account);
        return "success";
    }

    /**
     * 3.5.6 自定义类型转换器（了解）
     */
    @RequestMapping(value = "/saveUser")
    public String saveUser(User user){
        System.out.println("欢迎执行ParamController类中的saveUser方法！user:"+user);
        return "success";
    }

    /**3.5.7 在控制器中使用原生的ServletAPI对象*/
    @RequestMapping(value = "/testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("request:"+request);
        System.out.println("session:"+request.getSession());
        System.out.println("application:"+request.getSession().getServletContext());
        System.out.println("response:"+response);
        System.out.println("欢迎执行ParamController类中的testServlet方法！name:"+request.getParameter("name"));
        return "success";
    }
}
