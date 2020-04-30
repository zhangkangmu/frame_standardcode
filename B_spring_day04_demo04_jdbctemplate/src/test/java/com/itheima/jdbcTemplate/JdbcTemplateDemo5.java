package com.itheima.jdbcTemplate;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName JdbcTemplateDemo1
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/18 10:48
 * @Version V1.0
 */
public class JdbcTemplateDemo5 {

    // 在Dao中使用JdbcTemplate
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-anno.xml");
        AccountDao accountDao = ac.getBean("accountDao", AccountDao.class);
        // ID查询
//        Account account = accountDao.findById(3);
//        System.out.println(account);
        // 结合案例，按照名称查询，更新当前账号的金额
        Account account = accountDao.findByName("冯小刚");
        account.setMoney(account.getMoney()-100);
        accountDao.update(account);
    }
}

