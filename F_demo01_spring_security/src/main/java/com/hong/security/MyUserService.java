package com.hong.security;


import com.hong.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyuhong
 * Date:2020/4/12
 */
@Component
public class MyUserService implements UserDetailsService {

    public static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static Map<String, User> map = new HashMap<String, User>();
    /**
     * 通过静态代码块模式数据库
     * 也就是查看数据库中是否有这个用户
     */
    static {
       User user = new User();
        user.setUsername("admin");
        user.setPassword(encoder.encode("admin"));

        User user1 = new User();
        user1.setUsername("zhangsan");
        user1.setPassword(encoder.encode("123"));

        map.put(user.getUsername(), user);
        map.put(user1.getUsername(), user1);

    }

    /**
     * 根据用户名加载用户对象 (xml中配置了登录的页面,页面的数据会传到这里)
     *
     * 之前：登录功能通过用户名和密码到用户表验证
     * 现在：SpringSecurity密码是由框架来验证的
     * @param username 登录界面输入的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("========用户名:"+username+"========");
        //1.根据用户名到数据库中查询用户信息(造数据)
        User user = map.get(username);
        //2.判断用户名是否存在,不存在返回null
        if(user == null){
            return null;//用户根本不存在
        }
        //3.如果存在，授权********（等框架验证密码成功后，再授予）*********
        //获取密码(xml配置了没有加密方式,那就要增加{noop})
        ///String password = "{noop}"+user.getPassword();
        String password = user.getPassword();
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        list.add(new SimpleGrantedAuthority("add"));

        //String username: 用户名
        // String password：密码（根据用户名查询用户表 数据库中获取的）
        // Collection<? extends GrantedAuthority> authorities：授权
        return new org.springframework.security.core.userdetails.User(username,password,list);
    }
}
