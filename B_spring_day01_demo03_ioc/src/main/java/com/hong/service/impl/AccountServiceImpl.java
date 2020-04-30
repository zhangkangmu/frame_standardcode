package com.hong.service.impl;

import com.hong.dao.AccountDao;
import com.hong.dao.impl.AccountDaoImpl;
import com.hong.service.AccountService;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO

 * @Date 2020/3/14 11:09
 * @Version V1.0
 */
public class AccountServiceImpl implements AccountService {

    public AccountServiceImpl() {
        System.out.println("创建AccountServiceImpl对象！");
    }

    AccountDao accountDao = new AccountDaoImpl(); // 耦合

    public void saveAccount() {
        System.out.println("执行AccountServiceImpl类中的saveAccount方法");
        accountDao.save();
    }
}
