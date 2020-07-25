/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.dao.impl;

import com.itheima.TestStr;
import com.itheima.dao.TestDao;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月04日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class TestDaoImpl implements TestDao, InitializingBean {
	@Autowired
	TestStr testStr;

	public TestDaoImpl(TestStr testStr) {
		this.testStr = testStr;
	}

	@PostConstruct
	public void construct(){
		System.out.println("执行postcontruct,testStr:"+(testStr==null?"为空":"不为空"));
	}

	@Transactional
	@Override
	public String test() {
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void afterCommit() {
				System.out.println("提交了。。。。");
			}
		});
		return testStr.toString();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("执行了InitializingBean");
	}
}