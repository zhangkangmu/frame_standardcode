package com.hong.dao;

import com.hong.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户信息持久化接口
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.dao
 * @date 2020-4-23mybatis.type-aliases-packag
 */
//JpaRepository<目标,主键的数据类型>
public interface UserDao extends JpaRepository<User,Integer> {
}
