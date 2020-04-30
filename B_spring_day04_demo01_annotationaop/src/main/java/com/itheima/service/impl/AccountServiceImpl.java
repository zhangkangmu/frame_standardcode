package com.itheima.service.impl;

import com.itheima.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/16 14:45
 * @Version V1.0
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    /**切入点*/
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    /**切入点*/
    public void updateAccount(int i) {
        System.out.println("执行了更新"+i);
        // 故意抛出异常
        int ii = 10/0;
    }

    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
