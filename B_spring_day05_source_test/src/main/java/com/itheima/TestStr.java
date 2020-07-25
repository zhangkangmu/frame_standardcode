/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima;

import com.itheima.aware.TestAware;
import com.itheima.domain.User;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月04日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class TestStr implements DisposableBean {

	public static void testApplication(){
		User user1 = TestAware.getBean("user1", User.class);
		System.out.println(user1);
	}

	@Override
	public void destroy() throws Exception {

	}
}