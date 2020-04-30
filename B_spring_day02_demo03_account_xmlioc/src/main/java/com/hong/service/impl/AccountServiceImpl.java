package com.hong.service.impl;

import com.hong.dao.AccountDao;
import com.hong.domain.Account;
import com.hong.service.AccountService;

import java.util.List;

/**
 * @ClassName AccountServiceImpl
 @Company
 * @Date 2020/3/15 14:40
 * @Version V1.0
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void save(Account account) {
        accountDao.save(account);
    }

    public void update(Account account) {
        accountDao.update(account);
    }

    public void delete(Integer id) {
        accountDao.delete(id);
    }

    public Account findById(Integer id) {
        Account account = accountDao.findById(id);
        return account;
    }

    public List<Account> findAll() {
        List<Account> list = accountDao.findAll();
        return list;
    }
}
