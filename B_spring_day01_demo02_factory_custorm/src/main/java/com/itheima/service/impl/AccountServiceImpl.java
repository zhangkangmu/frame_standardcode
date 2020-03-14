package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.service.AccountService;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/14 11:09
 * @Version V1.0
 */
public class AccountServiceImpl implements AccountService {

    // AccountDao accountDao = new AccountDaoImpl(); // 耦合
    AccountDao dao; // 解耦

    public void saveAccount() {
        System.out.println("执行AccountServiceImpl类中的saveAccount方法");
        dao.save();
    }
}
