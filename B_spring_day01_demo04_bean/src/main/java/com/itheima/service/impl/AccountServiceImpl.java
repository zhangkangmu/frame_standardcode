package com.itheima.service.impl;

import com.itheima.service.AccountService;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/14 11:09
 * @Version V1.0
 */
public class AccountServiceImpl implements AccountService {

    // 默认开启
    public AccountServiceImpl() {
        System.out.println("创建AccountServiceImpl对象！");
    }

    public void saveAccount() {
        System.out.println("执行AccountServiceImpl类中的saveAccount方法");
    }

    // 初始化
    public void init(){
        System.out.println("初始化...");
    }

    // 销毁
    public void destroy(){
        System.out.println("销毁...");
    }
}
