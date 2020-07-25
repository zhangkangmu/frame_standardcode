/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import sun.util.logging.resources.logging;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年07月18日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Component("myAspect")
public class MyAspect {

    public void before(){
        System.out.println("前置通知...");
    }

    public void after(){
        System.out.println("后置通知...");
    }

    public void throwing(Throwable e){
        System.out.println("异常通知："+e.getMessage());
    }

    public void finallys(){
        System.out.println("最终通知...");
    }

    //ProceedingJoinPoint；代表切入点
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        //前置通知:
        before();
        //执行当前切入点的方法
        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
            //后置通知
            after();
         //异常通知
        } catch (Exception e) {
            throwing(e);
        } catch (Throwable throwable) {
            throwing(throwable);
        } finally {
            //最终通知
            finallys();
        }
        return proceed;
    }
}