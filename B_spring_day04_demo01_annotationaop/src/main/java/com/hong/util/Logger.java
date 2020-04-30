package com.hong.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName Logger
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/16 14:47
 * @Version V1.0
 */
/**切面*/
@Component("logger")
@Aspect
public class Logger {

    @Pointcut(value = "execution(* com.hong.service..*.*(..))")
    public void aa(){};

    /**通知*/
    // 前置通知
    // @Before(value = "aa()")
    public void beforePrintLog(JoinPoint jp){
        System.out.println("【前置通知】打印日志...方法名是："+jp.getSignature().getName());
    }


    // 后置通知
    // @AfterReturning(value = "aa()")
    public void afterReturningPrintLog(JoinPoint jp){
        System.out.println("【后置通知】打印日志...方法名是："+jp.getSignature().getName());
    }

    // 异常通知
    // @AfterThrowing(value = "aa()")
    public void afterThrowingPrintLog(JoinPoint jp){
        System.out.println("【异常通知】打印日志...方法名是："+jp.getSignature().getName());
    }

    // 最终通知
    // @After(value = "aa()")
    public void afterPrintLog(JoinPoint jp){
        System.out.println("【最终通知】打印日志...方法名是："+jp.getSignature().getName());
    }

    // 环绕通知，如果是环绕通知，需要手动调用目标对象的方法，此时不能使用JoinPoint，应该使用它的子类ProceedingJoinPoint
    @Around(value = "aa()")
    public Object aroundPrintLog(ProceedingJoinPoint jp){
        // System.out.println("【环绕通知】打印日志...方法名是："+jp.getSignature().getName());
        // 方法的返回值
        Object proceed = null;
        try {
            this.beforePrintLog(jp); // 前置通知
            // 调用目标对象的方法
            proceed = jp.proceed(jp.getArgs());
            this.afterReturningPrintLog(jp); // 后置通知
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.afterThrowingPrintLog(jp); // 异常通知
        } finally {
            this.afterPrintLog(jp); // 最终通知
        }
        return proceed;
    }
}
