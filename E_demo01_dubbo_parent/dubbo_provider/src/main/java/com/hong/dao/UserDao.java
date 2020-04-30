package com.hong.dao;

import com.hong.pojo.User;

// @Repository
public interface UserDao {

    User findById(Integer id);
}
