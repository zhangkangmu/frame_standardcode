package com.itheima.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.pojo
 * @date 2020-4-21
 */
@Component
@ConfigurationProperties(prefix = "user1")
public class User {
    //注意，此处的属性名要与yml声明的属性名一致
    private String name;
    private Integer age;
    private String addr;

    private List<User> userListObj;
    //省略getter与setter

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                ", userListObj=" + userListObj +
                '}';
    }

    public List<User> getUserListObj() {
        return userListObj;
    }

    public void setUserListObj(List<User> userListObj) {
        this.userListObj = userListObj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
