package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * @author zhangyuhong
 * @date 2018/02/29
 */
public interface UserDao {

    /**
     * 查询所有用户。及关联的帐号集合
     * @return
     */
    List<User> queryAll();
}
