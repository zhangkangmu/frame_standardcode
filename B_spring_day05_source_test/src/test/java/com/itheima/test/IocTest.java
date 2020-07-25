/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.test;

import com.itheima.TestStr;
import com.itheima.dao.TestDao;
import com.itheima.domain.Teacher;
import com.itheima.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月04日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class IocTest {
	ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");


	@Test
	public void test1(){
		/*User user1 = app.getBean("user1",User.class);
		System.out.println("当前是否包含teacher对应的定义信息:"+app.containsBean("teacher"));
		*//*TestStr.testApplication();*/
		User user1 = app.getBean("user10",User.class);
		System.out.println(user1);
	}


	@Test
	public void test2(){
		Teacher user1 = app.getBean("teacher", Teacher.class);
		System.out.println(user1);
		Teacher user2 = app.getBean("teacher",Teacher.class);
		System.out.println(user2==user1);
	}
}