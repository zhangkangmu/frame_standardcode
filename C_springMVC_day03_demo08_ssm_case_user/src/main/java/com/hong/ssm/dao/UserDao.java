package com.hong.ssm.dao;

import com.hong.ssm.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository // 该注解可以不加
public interface UserDao {

    // 注册保存
    @Insert(value = "insert into user(number,name,password,mobile,qq,email) values (#{number},#{name},#{password},#{mobile},#{qq},#{email})")
    int save(User user);



    // 总结：如果传递的是实体类，通过OGNL表达式读取实体类中的属性值
//    @Select(value = "select * from user where number=#{number} and password=#{password}")
//    User findUserByNumberAndPassword(User user);

    // 总结，如果传递的是多个参数，通过@Param注解指定参数名称，可以使用#{}查找对应的名称
//    @Select(value = "select * from user where number=#{a} and password=#{b}")
//    User findUserByNumberAndPassword(@Param(value = "a") String number, @Param(value = "b") String password);

    // 总结，如果传递的是Map，通过map的key，放置到#{}，获取key对应的value值
    @Select(value = "select * from user where number=#{number} and password=#{password}")
    User findUserByNumberAndPassword(Map map);
}
