package com.itheima.bean;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * @ClassName StaticFactroy
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
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
