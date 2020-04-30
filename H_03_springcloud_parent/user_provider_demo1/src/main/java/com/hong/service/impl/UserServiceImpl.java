package com.hong.service.impl;

import com.hong.dao.UserDao;
import com.hong.domain.User;
import com.hong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.service.impl
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
