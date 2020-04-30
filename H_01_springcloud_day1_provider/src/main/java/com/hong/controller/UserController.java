package com.hong.controller;

import com.hong.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.controller
 * @date 2020-4-23
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("list")
    public List<User> list() {
        List<User> users = new ArrayList<User>();
        users.add(new User("张三", "深圳", 25));
        users.add(new User("李四", "北京", 26));
        users.add(new User("王五", "上海", 27));
        return users;
    }
}
