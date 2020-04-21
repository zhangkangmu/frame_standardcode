package com.itheima.integration.controller;

import com.itheima.integration.pojo.User;
import com.itheima.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.integration.controller
 * @date 2020-4-21
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findAll")
    public List<User> findAll() {
        return userService.findAll();
    }
}
