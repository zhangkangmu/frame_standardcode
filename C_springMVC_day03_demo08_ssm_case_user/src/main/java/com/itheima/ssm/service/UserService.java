package com.itheima.ssm.service;

import com.itheima.ssm.domain.User;

public interface UserService {
    int save(User user);

    User findUserByNumberAndPassword(User user);
}
