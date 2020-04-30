package com.hong.factory;

import com.hong.service.AccountService;
import com.hong.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName BeanFactory
 * @Description TODO
 @Company
 * @Date 2020/3/16 11:42
 * @Version V1.0
 */
// 使用目标对象，创建代理对象，对Service类的方法增强,让他异常的时候就回滚
public class BeanFactory {
    // 目标对象
    AccountService accountService;
    // 事务管理器
    TransactionManager transactionManager;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    // 使用目标对象创建代理对象（使用JDK代理）
    public Object createProxyObject(){
        return (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue = null;
                        try {
                            // 设置事务手动提交
                            transactionManager.beginTransaction();
                            // 调用目标对象的方法
                            returnValue = method.invoke(accountService,args);
                            // 提交事务
                            transactionManager.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                            // 回滚事务
                            transactionManager.rollback();
                        } finally {
                            // 关闭事务（解绑）注意:关闭的放在这里
                            transactionManager.close();
                        }
                        return returnValue;
                    }
                });
    }
}
