package com.itheima.config;

import com.itheima.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.config
 * @date 2020-4-22
 */
@Configuration  //相当<beans></beans>
public class UserConfig {

    //相当于以前配置文件中的<bean></bean>
    @Bean
    //加条件判断-逻辑在OnClassCondition对象中的matches
    //如果条件成立，我们创建User对象，没有成立不创建,而条件的判断在OnClassCondition中,这个注解只是起到一个标志作用,为了让OnClassCondition识别到
    //@Conditional(OnClassCondition.class)
    @ConditionalOnMy(name = {"redis.clients.jedis.Jedis"})
    public User user() {
        return new User();
    }
}
