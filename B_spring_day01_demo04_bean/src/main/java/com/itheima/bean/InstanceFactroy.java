package com.itheima.bean;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * @ClassName StaticFactroy
 * @Description TODO

 * @Date 2020/3/14 15:39
 * @Version V1.0
 */
// 第三种：采用实例工厂（非静态的）实例化的方式
public class InstanceFactroy {

    // 提供一个非静态方法（实例方法），获取对象
    public AccountService createObject(){
        return new AccountServiceImpl();
    }
}
