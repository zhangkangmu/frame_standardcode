package com.itheima.exception;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SysExceptionHandler
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/20 15:37
 * @Version V1.0
 */
// 编写异常处理器（跳转到错误页面，并指定错误信息）
public class SysExceptionHandler implements HandlerExceptionResolver {


    // 如果Controller类抛出异常，在SysExceptionHandler中的resolveException方法中捕获；如果没有异常，就不会执行该方法
    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
        // 异常消息
        String message = "";
        if(ex instanceof SysException){
            message = ex.getMessage();
        }else{
            message = "非法操作，请联系管理员";
        }
        // 响应
        // 1：指定错误页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        // 2：输出错误信息
        mv.addObject("message",message);
        return mv;
    }
}
