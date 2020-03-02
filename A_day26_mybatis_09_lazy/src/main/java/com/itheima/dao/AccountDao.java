package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

/**
 * @author zhangyuhong
 * @date 2018/02/29
 */
public interface AccountDao {
    List<Account> queryAllAccount();

    /**
     * findByUid：查询某一用户的帐号集合
     */
    List<Account> findByUid(Integer uid);
}
