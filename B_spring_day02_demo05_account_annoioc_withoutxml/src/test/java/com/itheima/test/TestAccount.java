package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @ClassName TestAccount

 * @Date 2020/3/15 14:43
 * @Version V1.0
 */
public class TestAccount {

    // 保存
    @Test
    public void save(){
        // 加载Spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        Account account = new Account();
        account.setName("小张");
        account.setMoney(200f);
        accountService.save(account);
    }

    // 更新
    @Test
    public void update(){
        // 加载Spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountService", AccountService.class);
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
        // 加载Spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        accountService.delete(3);
    }

    // ID查询
    @Test
    public void findById(){
        // 加载Spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        Account account = accountService.findById(1);
        System.out.println(account);
    }

    // 查询所有
    @Test
    public void findAll(){
        // 加载Spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        List<Account> list = accountService.findAll();
        System.out.println(list);
    }
}
