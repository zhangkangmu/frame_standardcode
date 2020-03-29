package com.itheima.dao;

import com.itheima.pojo.User;

// @Repository
public interface UserDao {

    User findById(Integer id);
}
