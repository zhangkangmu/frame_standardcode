package com.itheima;

import org.springframework.stereotype.Component;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima
 * @date 2020-4-22
 */
 //@Component  是为了给启动类通过@Import(User.class) 导入 
 //注意，此时的User对象必须使用@Component注册Bean, 这个是直接导入,不需要通过UserConfig
//@Component
public class User {
}
