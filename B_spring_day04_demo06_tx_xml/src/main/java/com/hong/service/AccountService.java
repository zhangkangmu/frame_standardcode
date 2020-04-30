package com.hong.service;

import com.hong.domain.Account;

public interface AccountService {

    // ID查询
    Account findById(Integer id);
    // 账号名称查询（唯一数据）
    Account findByName(String name);
    // 更新
    void update(Account account);
    // 转账
    void transfer(String sourceName, String targetName, float money);
}
