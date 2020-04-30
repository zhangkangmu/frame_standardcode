package com.hong.ssm.service;

import com.hong.ssm.domain.User;

public interface UserService {
    int save(User user);

    User findUserByNumberAndPassword(User user);
}
