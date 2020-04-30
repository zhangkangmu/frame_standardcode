package com.hong.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hong.pojo.User;
import com.hong.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/27 14:43
 * @Version V1.0
 */
@Controller
@ResponseBody // 响应不会执行视图解析器，返回json，将User对象转换成json的数据
@RequestMapping(value = "/user")
public class UserController {

    // @Autowired
    @Reference // 相当于：<dubbo:reference interface="com.hong.service.UserService" id="userService"></dubbo:reference>
    UserService userService;

    // 地址栏输入：user/findById.do?id=1
    @RequestMapping(value = "/findById")  // 注意：不要写.do，.do在web.xml中已经指定了
    public User findById(Integer id){
        User user = userService.findById(id);
        return user;
    }
}
