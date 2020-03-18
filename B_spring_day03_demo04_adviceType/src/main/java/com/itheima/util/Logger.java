package com.itheima.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @ClassName Logger
 * @Description TODO
 * @Author ly
 * @Company
 * @Date 2020/3/16 14:47
 * @Version V1.0
 */
public class Logger {

    //JoinPoint:代表连接点,可以通过这个类获取被增强的方法名等信息
    // 前置通知
    public void beforePrintLog(JoinPoint jp){
        System.out.println("【前置通知】打印日志...方法名是："+jp.getSignature().getName());
    }

    // 后置通知
    public void afterReturningPrintLog(JoinPoint jp){
        System.out.println("【后置通知】打印日志...方法名是："+jp.getSignature().getName());
    }

    // 异常通知
    public void afterThrowingPrintLog(JoinPoint jp){
        System.out.println("【异常通知】打印日志...方法名是："+jp.getSignature().getName());
    }

    // 最终通知
    public void afterPrintLog(JoinPoint jp){
        System.out.println("【最终通知】打印日志...方法名是："+jp.getSignature().getName());
    }

    // 环绕通知，如果是环绕通知，需要手动调用目标对象的方法，此时不能使用JoinPoint，应该使用它的子类ProceedingJoinPoint
    //Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
    //该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
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
