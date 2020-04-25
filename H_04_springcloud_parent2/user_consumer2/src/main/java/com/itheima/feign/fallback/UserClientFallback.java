package com.itheima.feign.fallback;

import com.itheima.domain.User;
import com.itheima.feign.UserClient;
import org.springframework.stereotype.Component;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.feign.fallback
 * @date 2020-4-25
 */
@Component
public class UserClientFallback implements UserClient {

    /****
     * 全局的服务降级处理方法
     * @return
     */
    /*public User defaultFeignFailBack(){
        User user = new User();
        user.setUsername("Default-服务降级,Feign默认处理！");
        return  user;
    }*/

    /**
     * 添加UserController.findById方法的为熔断处理
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setUsername("Default-服务降级,Feign默认处理！");
        return  user;
    }
}
