package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/27 14:43
 * @Version V1.0
 */
@Controller
@ResponseBody // 响应不会执行视图解析器，返回json，将User对象转换成json的数据
@RequestMapping(value = "/user")
public class UserController {

    // 负载均衡策略：在集群负载均衡时，Dubbo 提供了多种均衡策略（包括随机random、轮询roundrobin、最少活跃调用数leastactive），缺省【默认】为random随机调用。
    // @Autowired
    @Reference(loadbalance = "roundrobin") // 相当于：<dubbo:reference interface="com.itheima.service.UserService" id="userService"></dubbo:reference>
    UserService userService;

    // 地址栏输入：user/findById.do?id=1
    @RequestMapping(value = "/findById")  // 注意：不要写.do，.do在web.xml中已经指定了
    public User findById(Integer id){
        User user = userService.findById(id);
        return user;
    }
}
