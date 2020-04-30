package com.hong.test;

import com.hong.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName TestAccountService
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/16 14:51
 * @Version V1.0
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestAccountService {

    @Autowired
    AccountService accountService;

    // 调用方法
    @Test
    public void testService(){
        accountService.saveAccount();
        accountService.updateAccount(10);
        int i = accountService.deleteAccount();
        System.out.println(i);
    }
}
