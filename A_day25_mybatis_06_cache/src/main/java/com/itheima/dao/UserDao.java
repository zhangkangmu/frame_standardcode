package com.itheima.dao;

import com.itheima.domain.User;

/**
 * @author liuyp
 * @date 2020/02/28
 */
public interface UserDao {
    User findById(Integer id);
}
