package com.itheima.test;

import com.itheima.ssm.dao.AccountDao;
import com.itheima.ssm.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ClassName TestSpringMybatis
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/22 9:45
 * @Version V1.0
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSpringMybatis {

    @Autowired
    AccountDao accountDao;

    @Test
    public void findAll(){
        List<Account> list = accountDao.findAll();
        for (Account account : list) {
            System.out.println(account);
        }
    }

    @Test
    public void save(){
        Account account = new Account();
        account.setName("赵六");
        account.setMoney(3500d);
        accountDao.save(account);
    }
}
