/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.config;

import com.itheima.TestStr;
import com.itheima.config.condition.OracleCondition;
import com.itheima.dao.TestDao;
import com.itheima.dao.impl.TestDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月06日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Conditional(OracleCondition.class)
@Order(2)
@ComponentScan(value = "com.itheima.dao",includeFilters = {
        @ComponentScan.Filter(value = {
                TestDaoImpl.class},type=FilterType.ASSIGNABLE_TYPE)})
@Configuration
public class MyConfiguration implements configs{

    @Bean
    public MyBeanFactoryPostProcessor getMyBeanFactoryPostProcessor(){
        return new MyBeanFactoryPostProcessor();
    }

  /*  @Value("${db.name}")
    @Bean
    public TestStr getTestStr(String dbname){
        return new TestStr(dbname);
    }*/
}