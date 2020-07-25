/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.config;

import com.itheima.config.importselector.DbConfiSelector;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月11日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Order(1)
@Configuration
@PropertySource({"classpath:db.properties","classpath:dbselect.properties"})
@ImportResource({"classpath:applicationContext2.xml"})
@Import(DbConfiSelector.class)
@EnableAspectJAutoProxy
@EnableTransactionManagement
//@Import({MyConfiguration.class,MyConfiguration2.class})
public class PropertieConfiguration {

}