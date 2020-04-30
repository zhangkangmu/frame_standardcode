package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.UserDao;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/27 11:37
 * @Version V1.0
 */
@Transactional
@Service(protocol = "dubbo") // 相当于：<dubbo:service interface="com.itheima.service.UserService" ref="userService"></dubbo:service>
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User findById(Integer id) {
        System.out.println("【服务提供者中的方法被调用...】传递的id："+id);
        User user = userDao.findById(id);
        return user;
    }
}
