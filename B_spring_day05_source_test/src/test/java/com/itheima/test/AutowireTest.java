/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.test;

import com.itheima.config.MyConfiguration;
import com.itheima.config.PropertieConfiguration;
import com.itheima.dao.TestDao;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月09日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class AutowireTest {
    ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    //ApplicationContext app = new AnnotationConfigApplicationContext(PropertieConfiguration.class);
    @Test
    public void test1(){

        System.out.println(app.getBean("testDao", TestDao.class).test());
        //System.out.println(app.getBean("classRoom1"));
    }

    @Test
    public void test2(){
        System.out.println(app.getBean(AccountService.class).findAll());
    }
}