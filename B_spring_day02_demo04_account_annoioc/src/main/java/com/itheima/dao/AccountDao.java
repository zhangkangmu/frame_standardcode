package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

public interface AccountDao {

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
