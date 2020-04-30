package com.hong.service.impl;

import com.hong.dao.AccountDao;
import com.hong.service.AccountService;

import java.util.Date;

/**
 * @ClassName AccountServiceImpl
 @Date 2020/3/14 11:09
 * @Version V1.0
 */
public class AccountServiceImpl implements AccountService {

    // 创建Dao（注入）
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    private String name;     // 值
    private Integer age;     // 值
    private Date birthday;  // 对象

    // 创建无参构造函数（spring默认创建对象，需要无参）
    public AccountServiceImpl() {

    }

    // 有参的构造函数（用来注入3个属性的值）
    public AccountServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("执行AccountServiceImpl类中的saveAccount方法，name="+name+"   age="+age+"    birthday="+birthday);
        accountDao.save();
    }

}
