package com.itheima.test;

import com.itheima.dao.AccountDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Account;
import com.itheima.domain.User;
import com.itheima.domain.User2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Mybatis的注解开发
 * @author zhangyuhong
 * @date 2018/02/29
 */
public class MybatisAnnotationTest {

    private InputStream is;
    private SqlSession session;
    private UserDao userDao;
    private AccountDao accountDao;

    @Test
    public void testQueryAll(){
        List<User> userList = userDao.queryAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById(){
        User user = userDao.findById(41);
        System.out.println(user);
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("小泽");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("广东深圳");


        System.out.println("保存前：" + user);
        userDao.save(user);
        session.commit();
        System.out.println("保存后：" + user);
    }

    @Test
    public void testEdit(){
        User user = userDao.findById(54);

        user.setSex("女");

        userDao.edit(user);
        session.commit();
    }

    @Test
    public void testDelete(){
        userDao.delete(54);
        session.commit();
    }

    @Test
    public void testTotalCount(){
        int totalCount = userDao.totalCount();
        System.out.println(totalCount);
    }

    @Test
    public void testSearchByUsername(){
        List<User> userList = userDao.searchByUsername("%王%");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testSearchByUsername2(){
        List<User> userList = userDao.searchByUsername2("王");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryAllUser2(){
        List<User2> user2List = userDao.queryAllUser2();
        for (User2 user2 : user2List) {
            System.out.println(user2);
        }
    }

    /**
     * 查询所有帐号。及关联的用户
     * 用懒加载的方式实现
     */
    @Test
    public void testQueryAllAccount(){
        List<Account> accounts = accountDao.queryAll();
        for (Account account : accounts) {
            System.out.println(account.getId()+","+account.getMoney());

            System.out.println(account.getUser());
        }
    }

    /**
     * 查询所有用户。及关联的帐号集合
     * 用懒加载的方式实现
     */
    @Test
    public void testQueryAllUser(){
        List<User> userList = userDao.queryAllUserWithAccounts();
        for (User user : userList) {
            System.out.println(user.getUsername() +","+ user.getSex());

            System.out.println(user.getAccounts());
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
