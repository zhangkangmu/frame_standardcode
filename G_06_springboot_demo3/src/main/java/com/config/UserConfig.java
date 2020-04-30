package com.config;

import com.itheima.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.config
 * @date 2020-4-22
 */
@Configuration
public class UserConfig {

    @Bean
    public User user() {
        return new User();
    }
}
