package com.hong.service.impl;

import com.hong.dao.AccountDao;
import com.hong.domain.Account;
import com.hong.service.AccountService;

import java.util.List;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
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

    public void transfer(String sourceName, String targetName, int money) {
        // 1：使用sourceName查询aaa的账号，获取Account对象
        Account sourceAccount = accountDao.findAccountByName(sourceName);
        // 2：使用targetName查询bbb的账号，获取Account对象
        Account targetAccount = accountDao.findAccountByName(targetName);
        // 3：将aaa的账号 - money
        sourceAccount.setMoney(sourceAccount.getMoney()-money);
        // 4：将bbb的账号 + money
        targetAccount.setMoney(targetAccount.getMoney()+money);
        // 5：更新aaa的账号
        accountDao.update(sourceAccount);
        // 抛出一个异常
        int i = 10/0;
        // 6：更新bbb的账号
        accountDao.update(targetAccount);
    }
}
