package com.hong.ssm.service.impl;

import com.hong.ssm.dao.UserDao;
import com.hong.ssm.domain.User;
import com.hong.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/22 14:50
 * @Version V1.0
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public int save(User user) {
        int row = userDao.save(user);
        return row;
    }

    @Override
    public User findUserByNumberAndPassword(User user) {
        // 方案一：使用用户名和密码作为查询条件，查询用户信息（传递User到Dao）
        // User u = userDao.findUserByNumberAndPassword(user);
        // 方案二：使用用户名和密码作为查询条件，查询用户信息（传递2个字符串的条件到Dao）
        // User u = userDao.findUserByNumberAndPassword(user.getNumber(),user.getPassword());

        // 方案三：使用用户名和密码作为查询条件，查询用户信息（传递Map到Dao）
        Map map = new HashMap();
        map.put("number",user.getNumber());
        map.put("password",user.getPassword());
        User u = userDao.findUserByNumberAndPassword(map);
        return u;
    }
}
