/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.service.impl;

import com.itheima.controller.AccountController;
import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年07月29日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Service
@Data
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Override
    public List<Account> findAll() {
        //System.out.println("accountController:"+accountController);
        List<Account> list = accountDao.findAll();
        return list;
    }

    @Override
    public boolean add(Account account) {
        return accountDao.add(account)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return accountDao.delete(id)>0;
    }

    @Override
    public Account selectById(Integer id) {
        return accountDao.selectById(id);
    }

    @Override
    public boolean update(Account account) {
        return accountDao.update(account);
    }
}