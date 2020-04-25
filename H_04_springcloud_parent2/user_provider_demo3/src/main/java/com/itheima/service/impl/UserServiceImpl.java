package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.service.impl
 * @date 2020-4-23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUserId(Integer id) {
        User user = userDao.findById(id).get();
        return user;
    }
}
