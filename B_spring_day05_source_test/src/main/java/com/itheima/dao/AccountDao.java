/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.dao;


import com.itheima.domain.Account;

import java.util.List;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年07月30日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public interface AccountDao {

    public List<Account> findAll();

    public int add(Account account);

    int delete(Integer id);

    Account selectById(Integer id);

    boolean update(Account account);
}