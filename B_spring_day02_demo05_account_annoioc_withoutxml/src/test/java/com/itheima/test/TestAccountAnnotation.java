package com.itheima.test;

import com.itheima.SpringConfiguration;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @ClassName TestAccount

 * @Date 2020/3/15 14:43
 * @Version V1.0
 */
public class TestAccountAnnotation {

    // 保存
    @Test
    public void save(){
        // 加载Spring容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
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
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
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
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        accountService.delete(3);
    }

    // ID查询
    @Test
    public void findById(){
        // 加载Spring容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        Account account = accountService.findById(1);
        System.out.println(account);
    }

    // 查询所有
    @Test
    public void findAll(){
        // 加载Spring容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AccountService accountService = ac.getBean("accountService", AccountService.class);
        List<Account> list = accountService.findAll();
        System.out.println(list);
    }

    // 测试单例和多例
    @Test
    public void testScope(){
        // 加载Spring容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        QueryRunner queryRunner = ac.getBean("queryRunner", QueryRunner.class);
        QueryRunner queryRunner2 = ac.getBean("queryRunner", QueryRunner.class);
        System.out.println(queryRunner);
        System.out.println(queryRunner2);
    }
}
