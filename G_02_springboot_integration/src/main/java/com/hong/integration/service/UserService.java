package com.hong.integration.service;

import com.hong.integration.pojo.User;

import java.util.List;

/**
 * 用户信息业务逻辑接口
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.integration.dao
 * @date 2020-4-21
 */
public interface UserService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();
}
