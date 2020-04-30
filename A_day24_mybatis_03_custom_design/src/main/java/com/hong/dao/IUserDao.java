package com.hong.dao;

import com.hong.domain.User;
import com.hong.mybatis.annotations.Select;

import java.util.List;

/**
 * @author
 *
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
