package com.itheima.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/22 16:12
 * @Version V1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    // 在访问Controller类的方法之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("当前访问的url："+request.getServletPath());
        //跳转到Controller类之前，判断
        // 获取globle_user的Session
        Object o = request.getSession().getAttribute("globle_user");
        // * 判断如果存在globle_user的Session，表示可以访问，放行
        if(o!=null){
            return true;// 放行
        }
        //  * 判断如果不存在globle_user的Session，表示不可以访问，不能放行
        else{
            // 跳转到登录页面，让用户重新登录。
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
    }
}
