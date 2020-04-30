package com.hong.test;

import com.hong.dao.AccountDao;
import com.hong.dao.UserDao;
import com.hong.domain.Account;
import com.hong.domain.User;
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
 * Mybatis的延迟加载测试类
 * @author zhangyuhong
 * @date 2018/02/29
 */
public class MybatisLazyTest {

    private InputStream is;
    private SqlSession session;
    private UserDao userDao;
    private AccountDao accountDao;

    @Test
    public void testQueryAllAccount(){
        //User user = userDao.findByUid(41);
        //System.out.println(user);

        System.out.println("---------------------");

        List<Account> accounts = accountDao.queryAllAccount();
        for (Account account : accounts) {
            //懒加载的效果：当不需要使用帐号关联的用户时，不执行查询用户的SQL。
            System.out.println(account.getId() + ", " +account.getMoney() + ", " + account.getUid());

            //当使用帐号关联的用户时，Mybatis才会执行查询用户的SQL
            System.out.println(account.getUser());
        }
    }

    @Test
    public void testQueryAllUser(){
        List<User> userList = userDao.queryAllUser();
        for (User user : userList) {
            //懒加载：如果不使用用户关联的帐号集合，Mybatis不会执行查询帐号的SQL语句
            System.out.println(user.getUsername()+","+user.getSex());

            //如果我们使用到了用户关联的帐号信息，Mybatis才会执行 查询帐号的SQL语句
            List<Account> accounts = user.getAccounts();
            System.out.println(accounts);
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
