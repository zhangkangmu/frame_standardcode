/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.config;

import com.itheima.TestStr;
import com.itheima.config.condition.MysqlCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月06日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Conditional(MysqlCondition.class)
@Order(2)
@ComponentScan("com.itheima.dao")
@Configuration
public class MyConfiguration2 {

    @Bean
    public MyBeanFactoryPostProcessor getMyBeanFactoryPostProcessor(){
        return new MyBeanFactoryPostProcessor();
    }

}