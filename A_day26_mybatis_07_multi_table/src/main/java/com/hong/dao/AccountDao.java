package com.hong.dao;

import com.hong.domain.Account;
import com.hong.domain.UserAccount;

import java.util.List;

/**
 * @author zhangyuhong
 * @date 2018/02/29
 */
public interface AccountDao {

    List<UserAccount> queryAllUserAccount();

    /**
     * 查询所有帐号。以及关联的用户
     */
    List<Account> queryAll();
}
