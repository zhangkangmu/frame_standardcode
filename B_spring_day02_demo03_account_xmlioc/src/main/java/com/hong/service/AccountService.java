package com.hong.service;

import com.hong.domain.Account;

import java.util.List;

public interface AccountService {
    // 保存
    public void save(Account account);

    // 更新
    public void update(Account account);

    // 删除
    public void delete(Integer id);

    // ID 查询
    public Account findById(Integer id);

    // 查询所有
    List<Account> findAll();
}
