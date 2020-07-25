/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.service;


import com.itheima.domain.Account;

import java.util.List;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年07月29日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public interface AccountService {
    List<Account> findAll();

    boolean add(Account account);

    boolean delete(Integer id);

    Account selectById(Integer id);

    boolean update(Account account);
}