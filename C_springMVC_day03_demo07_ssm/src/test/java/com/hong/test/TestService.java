package com.hong.test;

import com.hong.ssm.domain.Account;
import com.hong.ssm.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ClassName TestService
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/22 9:45
 * @Version V1.0
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestService {

    @Autowired
    AccountService accountService;

    @Test
    public void findAll(){
        List<Account> list = accountService.findAll();
    }

}
