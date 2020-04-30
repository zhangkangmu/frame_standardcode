package com.hong.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @ClassName TransactionManager
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/16 10:23
 * @Version V1.0
 */
/**切面*/
@Component
@Aspect
public class TransactionManager {

    @Pointcut(value = "execution(* com.hong.service..*.*(..))")
    public void aa(){}

    // 连接对象
    @Autowired
    ConnectionUtils connectionUtils;

    // 开启事务（不设置事务自动提交）
    /**通知*/
    // @Before(value = "aa()")
    public void beginTransaction(){
        try {
            System.out.println("【前置通知】");
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 提交事务
    // @AfterReturning(value = "aa()")
    public void commit(){
        try {
            System.out.println("【后置通知】");
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 回滚事务
    // @AfterThrowing(value = "aa()")
    public void rollback(){
        try {
            System.out.println("【异常通知】");
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭事务、解绑（线程和Connection对象完成解绑）
    // @After(value = "aa()")
    public void close(){
        try {
            System.out.println("【最终通知】");
            connectionUtils.getThreadConnection().close();
            connectionUtils.releaseConnection(); // 解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Around(value = "aa()")
    public Object aroundAdvice(ProceedingJoinPoint jp){
        // 手动调用目标对象
        Object proceed = null;
        try {
            this.beginTransaction();
            proceed = jp.proceed(jp.getArgs());
            this.commit();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.rollback();
        } finally {
            this.close();
        }
        return proceed;
    }
}
