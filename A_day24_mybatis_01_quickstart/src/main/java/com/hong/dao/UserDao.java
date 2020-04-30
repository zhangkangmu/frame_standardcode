package com.hong.dao;

import com.hong.domain.User;

import java.util.List;

/**
 * 映射器。是接口，不需要实现类
 * @author zhangyuhong
 * @date 2018/02/27
 */
public interface UserDao {
    List<User> queryAll();

    /*User findById(Integer id);*/
}
