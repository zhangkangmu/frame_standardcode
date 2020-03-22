package com.itheima.ssm.service;

import com.itheima.ssm.domain.Account;

import java.util.List;

public interface AccountService {

    // 查询所有
    public List<Account> findAll();
    // 保存
    public void save(Account account);
}
