package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.AccountDao;
import com.itheima.ssm.domain.Account;
import com.itheima.ssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/22 9:30
 * @Version V1.0
 */

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        System.out.println("访问AccountServiceImpl类中的findAll方法！");
        List<Account> list = accountDao.findAll();
        return list;
    }

    @Override
    public void save(Account account) {
        System.out.println("访问AccountServiceImpl类中的save方法！");
        accountDao.save(account);
        // 操作其他业务
        // int i = 10/0; // 抛出异常
    }
}
