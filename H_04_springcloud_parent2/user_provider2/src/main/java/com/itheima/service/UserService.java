package com.itheima.service;

import com.itheima.domain.User;

/**
 * 用户信息业务逻辑接口
 */
public interface UserService {
    /***
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    User findByUserId(Integer id);
}
