package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * @author zhangyuhong
 * @date 2018/02/29
 */
public interface UserDao {
//    给accountdao调用的.一对一懒加载
    User findByUid(Integer uid);

//对多 的懒加载
    List<User> queryAllUser();
}
