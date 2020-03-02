package com.itheima.dao;

import com.itheima.domain.Account;
import com.itheima.domain.UserAccount;

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
