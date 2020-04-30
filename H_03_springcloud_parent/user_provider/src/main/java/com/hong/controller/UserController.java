package com.hong.controller;

import com.hong.domain.User;
import com.hong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    /***
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "find/{id}")
    //同名的话(value = "id")就可以不用写了
    public User findById(@PathVariable(value = "id") Integer id){
        User user = userService.findByUserId(id);
        user.setUsername(user.getUsername() + " user-provider");
        return user;
    }
}
