/**
 * Copyright (c) 2020 itheima.com, All rights reserved.
 *
 * @Author: lvyang
 */
package com.itheima.test;

import org.junit.Test;
import org.springframework.beans.factory.ObjectFactory;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2020年06月17日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class FunctionInterfaceTest {

    @Test
    public void test(){
        String str = getStr(() -> {
            return function();
        });//1
        System.out.println(str);//5
    }

    public String function(){
        return "ok";//3
    }


    public String getStr(ObjectFactory<?> singletonFactory){
        Object object = singletonFactory.getObject();//2
        return object.toString();//4
    }
}