package com.hong.service.impl;

import com.hong.service.AccountService;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author ly
 * @Company
 * @Date 2020/3/16 14:45
 * @Version V1.0
 */
public class AccountServiceImpl implements AccountService {

    public void saveAccount() {
        System.out.println("执行了保存");
    }

    public void updateAccount(int i) {
        System.out.println("执行了更新"+i);

    }

    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
