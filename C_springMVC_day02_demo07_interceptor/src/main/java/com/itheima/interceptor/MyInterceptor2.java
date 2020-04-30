package com.itheima.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MyInterceptor
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/20 16:07
 * @Version V1.0
 */
public class MyInterceptor2 implements HandlerInterceptor {

    /**1：preHandle方法是controller方法执行前拦截的方法
     可以使用request或者response跳转到指定的页面
     return true放行，执行下一个拦截器，如果没有拦截器，执行controller中的方法。
     return false不放行，不会执行controller中的方法。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("【拦截器2】在执行Controller类方法之前执行...");
        // request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request,response);
        return true;
    }
    /**2：postHandle是controller方法执行后执行的方法，在JSP视图执行前。
     可以使用request或者response跳转到指定的页面
     如果指定了跳转的页面，那么controller方法跳转的页面将不会显示。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("【拦截器2】在执行Controller类方法之后执行，在视图JSP方法之前执行");
        // request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request,response);
    }
    /**3：afterCompletion方法是在JSP执行后执行（用的不多）
     如果一个拦截器：request或者response不能再跳转页面，执行Controller类定义页面。
     如果多个拦截器：request或者response不能再跳转页面，抛出异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("【拦截器2】在视图JSP方法之后执行");
    }
}
