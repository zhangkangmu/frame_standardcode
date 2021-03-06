package com.hong.integration.controller;

import com.hong.integration.pojo.User;
import com.hong.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.integration.controller
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
