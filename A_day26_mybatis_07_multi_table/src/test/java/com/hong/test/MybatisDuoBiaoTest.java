package com.hong.test;

import com.hong.dao.AccountDao;
import com.hong.dao.UserDao;
import com.hong.domain.Account;
import com.hong.domain.User;
import com.hong.domain.UserAccount;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Mybatis的多表查询测试类
 * @author zhangyuhong
 * @date 2018/02/29
 */
public class MybatisDuoBiaoTest {

    private InputStream is;
    private SqlSession session;
    private UserDao userDao;
    private AccountDao accountDao;

    @Test
    public void testQueryAllUserAccount(){
        List<UserAccount> userAccounts = accountDao.queryAllUserAccount();
        for (UserAccount userAccount : userAccounts) {
            System.out.println(userAccount);
        }
    }

    @Test
    public void testQueryAllAccount(){
        List<Account> accounts = accountDao.queryAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testQueryAllUser(){
        List<User> userList = userDao.queryAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Before
    public void before() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
        accountDao = session.getMapper(AccountDao.class);
    }

    @After
    public void after() throws IOException {
        session.close();
        is.close();
    }
}
