package com.hong.bean;

import com.hong.service.AccountService;
import com.hong.service.impl.AccountServiceImpl;

/**
 * @ClassName StaticFactroy
 * @Description TODO

 * @Date 2020/3/14 15:39
 * @Version V1.0
 */
// l 第二种：采用静态工厂实例化的方式
public class StaticFactroy {

    // 提供一个静态方法，获取对象
    public static AccountService createObject(){
        return new AccountServiceImpl();
    }
}
