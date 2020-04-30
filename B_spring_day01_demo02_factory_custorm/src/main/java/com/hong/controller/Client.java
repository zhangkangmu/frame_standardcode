package com.hong.controller;

import com.hong.factory.BeanFactroy;
import com.hong.service.AccountService;

/**
 * @ClassName Client
 * @Description TODO

 * @Date 2020/3/14 11:10
 * @Version V1.0
 */
public class Client {

    public static void main(String[] args) {
        // AccountService accountService = new AccountServiceImpl();
        // 使用工厂的方式获取AccountServiceImpl();对象
        AccountService accountService = (AccountService)BeanFactroy.getBean("accountService");
        accountService.saveAccount();
    }
}
