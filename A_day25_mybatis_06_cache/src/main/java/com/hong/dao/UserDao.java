package com.hong.dao;

import com.hong.domain.User;

/**
 * @author zhangyuhong
 * @date 2018/02/28
 */
public interface UserDao {
    User findById(Integer id);
}
