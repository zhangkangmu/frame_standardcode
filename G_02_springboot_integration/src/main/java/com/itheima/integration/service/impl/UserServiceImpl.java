package com.itheima.integration.service.impl;

import com.itheima.integration.dao.UserMapper;
import com.itheima.integration.pojo.User;
import com.itheima.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.integration.service.impl
 * @date 2020-4-21
 */
@Service
public class UserServiceImpl implements UserService {
    //为了编译的时候去掉警告的提示的注解,仅仅为了不爆红
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<User> findAll() {
        //1.先查询Redis的用户数据
        List<User> userList = (List<User>) redisTemplate.boundValueOps("users").get();
        //2.缓存没有，查询数据-把数据放入缓存
        if (userList == null || userList.size() < 1) {
            userList = userMapper.findAll();
            //把用户数据放入缓存中
            redisTemplate.boundValueOps("users").set(userList);
        }else {
            System.out.println("从缓存中读取了用户列表....");
        }
        //3.缓存有，直接返回缓存数据
        return userList;
    }
}
