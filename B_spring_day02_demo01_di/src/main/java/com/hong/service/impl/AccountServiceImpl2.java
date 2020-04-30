package com.hong.service.impl;

import com.hong.service.AccountService;

import java.util.Date;

/**
 * @ClassName AccountServiceImpl
 @Date 2020/3/14 11:09
 * @Version V1.0
 */
public class AccountServiceImpl2 implements AccountService {

    private String name;     // 值
    private Integer age;     // 值
    private Date birthday;  // 对象

    // 用于注入
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("执行AccountServiceImpl2类中的saveAccount方法，name="+name+"   age="+age+"    birthday="+birthday);
    }

}
