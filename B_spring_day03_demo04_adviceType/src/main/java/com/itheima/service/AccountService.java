package com.itheima.service;

public interface AccountService {

    /**
     * 模拟保存账户（无返回值，无参数）
     */
    void saveAccount();

    /**
     * 模拟更新账户（无返回值，有参数）
     * @param i
     */
    void updateAccount(int i);

    /**
     * 删除账户（有返回值，无参数）
     * @return
     */
    int deleteAccount();
}
