package com.itheima.test;

import com.itheima.service.AccountService;
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
 * @Date 2020/3/18 14:52
 * @Version V1.0
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestAccountService {

    @Autowired
    AccountService accountService;

    // 转账案例
    @Test
    public void transfer(){
        accountService.transfer("aaa","bbb",100f);
    }
}
