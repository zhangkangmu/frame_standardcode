package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ClassName TestAccount
 * @Description TODO
 * @Author ly
 * @Company
 * @Date 2020/3/15 14:43
 * @Version V1.0
 */
// 1:spring整合Junit
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestAccountJunit {

    @Autowired
    @Qualifier(value = "accountServiceProxy")
    AccountService accountService;

    // 保存
    @Test
    public void save(){
        Account account = new Account();
        account.setName("小张");
        account.setMoney(200f);
        accountService.save(account);
    }

    // 更新
    @Test
    public void update(){
        /**对象更新（更新所有字段）*/
//        Account account = new Account();
//        account.setId(3);
//        account.setName("小李");
//        account.setMoney(200f);
        /**ID查询，再更新（更新某个字段）*/
        Account account = accountService.findById(3);
        account.setMoney(500f);
        accountService.update(account);
    }

    // 删除
    @Test
    public void delete(){
        accountService.delete(3);
    }

    // ID查询
    @Test
    public void findById(){
        Account account = accountService.findById(1);
        System.out.println(account);
    }

    // 查询所有
    @Test
    public void findAll(){
        List<Account> list = accountService.findAll();
        System.out.println(list);
    }

    // 【需求】：姓名为aaa的账号取出100元给姓名为bbb的账号
    @Test
    public void transfer(){
        accountService.transfer("aaa","bbb",100);
    }
}
