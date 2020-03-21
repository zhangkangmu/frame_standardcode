package com.itheima.controller;

import com.itheima.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/19 9:58
 * @Version V1.0
 */
@Controller  // 该注解表示该类控制器
@RequestMapping(value = "/user") // 一级路径
public class UserController {

    @RequestMapping(value = "/testResponse")
    public String sayHello(){
        System.out.println("欢迎访问UserController类中的testResponse的方法！");
        return "success";
    }

    // 2.2.1 返回字符串
    @RequestMapping(value = "/testReturnString")
    public String testReturnString(){
        System.out.println("欢迎访问UserController类中的testReturnString的方法！");
        return "success";
    }

    // 2.2.1 返回字符串（表单回显）
    /**
     * Model model：模型（Map机构），用来封装响应的数据，相当于Request作用域
     * @param model
     * @return
     */
    @RequestMapping(value = "/userUpdate")
    public String userUpdate(Model model){
        System.out.println("欢迎访问UserController类中的userUpdate的方法！");
        User user = new User();
        user.setUsername("张三");
        user.setAge(22);
        model.addAttribute("user",user);
        return "success";
    }

    // 2.2.1 返回字符串（表单回显，更新保存-->User user）
    @RequestMapping(value = "/update")
    public String update(User user){
        System.out.println("欢迎访问UserController类中的update的方法！user:"+user);
        return "success";
    }

    // 2.2.2 返回值是void（了解）
    /**
     * 现象：404：/springmvc_day02_response/WEB-INF/page/user/testVoid.jsp
     * 结论：返回void，也会执行视图解析器，将访问的路径user/testVoid作为，视图解析器中间的部分
     * 如果不想通过视图解析器跳转，也可以使用request和response完成转发和重定向
     */
    @RequestMapping(value = "/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("欢迎访问UserController类中的testVoid的方法！");
        // 转发（1次请求，浏览器显示1次请求的地址：http://localhost:8080/springmvc_day02_response/user/testVoid）
        // request.getRequestDispatcher("/WEB-INF/page/success.jsp").forward(request,response);
        // 重定向（2次请求，浏览器显示第2次请求的地址：http://localhost:8080/springmvc_day02_response/WEB-INF/page/success.jsp）
        // response.sendRedirect("WEB-INF/page/success.jsp");
        // 重定向：http://localhost:8080/springmvc_day02_response/user/testReturnString
        // response.sendRedirect(request.getContextPath()+"/user/testReturnString");
        // ajax的方式
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println("访问成功，haha");
        return;
    }


    // 2.2.3 返回值是ModelAndView对象
    /**
     * ModelAndView
     *   * 既有Model的功能
     *   * 也有View的功能（执行视图解析器）
     * @return
     */
    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView() {
        System.out.println("欢迎访问UserController类中的testModelAndView的方法！");
        ModelAndView mv = new ModelAndView();
        // 视图：
        mv.setViewName("success");
        // 模型
        List<User> list = new ArrayList<>();
        User u = new User();
        u.setUsername("张三");
        u.setAge(22);

        User u2 = new User();
        u2.setUsername("李四");
        u2.setAge(20);
        list.add(u);
        list.add(u2);
        mv.addObject("list",list);
        return mv;
    }

    // 2.3.1 forward请求转发
    // 2.3.2 redirect重定向
    @RequestMapping(value = "/testForwardOrRedirect")
    public String testForwardOrRedirect() {
        System.out.println("欢迎访问UserController类中的testForwardOrRedirect的方法！");
        // return "forward:/WEB-INF/page/success.jsp"; // forward关键字的时候，视图解析器将失效--可以成功跳转
        // return "forward:/user/userUpdate"; // 此时的路径：http://localhost:8080/springmvc_day02_response/user/testForwardOrRedirect
        // return "redirect:/WEB-INF/page/success.jsp"; //错误写法，重定向不能指定到WEB-INF/下的路径
        return "redirect:/user/userUpdate";// 此时的路径：http://localhost:8080/springmvc_day02_response/user/userUpdate
    }

    // 处理Ajax请求（导入jackson，导入fastjson）
    // @RequestBody：将json的数据，转换成实体对象
    // @ResponseBody：将实体对象，转换成json的数据
    @RequestMapping(value = "/testAjax")
    @ResponseBody
    public User testAjax(@RequestBody User user) {
        // System.out.println("欢迎访问UserController类中的testAjax的方法！body:"+body); // @RequestBody String body: body:{"username":"张三","age":"22"}
        System.out.println("欢迎访问UserController类中的testAjax的方法！user:"+user);
        User u = new User();
        u.setUsername("冯小刚");
        u.setAge(50);
        return u; // 将User对象转换json对象
    }
}
