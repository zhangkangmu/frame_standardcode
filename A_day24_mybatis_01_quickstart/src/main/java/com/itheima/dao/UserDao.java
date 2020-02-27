package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * 映射器。是接口，不需要实现类
 * @author liuyp
 * @date 2020/02/27
 */
public interface UserDao {
    List<User> queryAll();

    /*User findById(Integer id);*/
}
