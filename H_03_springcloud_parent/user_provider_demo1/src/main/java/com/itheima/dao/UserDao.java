package com.itheima.dao;

import com.itheima.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户信息持久化接口
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.dao
 * @date 2020-4-23
 */
//JpaRepository<目标,主键的数据类型>
public interface UserDao extends JpaRepository<User,Integer> {
}
