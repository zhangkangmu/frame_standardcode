package com.itheima.dao;

import com.itheima.domain.User;

/**
 * @author zhangyuhong
 * @date 2018/02/28
 */
public interface UserDao {
    User findById(Integer id);
}
