package com.itheima.integration.dao;

import com.itheima.integration.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户信息持久化接口
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.integration.dao
 * @date 2020-4-21
 */
//SpringBoot整合Mybatis时用到这个注解来注册dao
@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();
}
