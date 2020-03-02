package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * @author zhangyuhong
 * @date 2018/02/29
 */
public interface UserDao {
    User findByUid(Integer uid);

    List<User> queryAllUser();
}
