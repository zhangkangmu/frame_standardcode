package com.hong.test;

import com.hong.dao.UserDao;
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
import java.util.Date;
import java.util.List;

/**
 * @author zhangyuhong
 * @date 2018/02/27
 */
public class MybatisCurdTest {

    private UserDao dao;
    private InputStream is;
    private SqlSession session;

    @Test
    public void testQueryAll() {
        List<User> userList = dao.queryAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("小蔡");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("广东深圳");


        System.out.println("添加之前：" + user);
        //调用dao的方法
        int save = dao.save(user);
        //提交事务。Mybatis里执行完成DML语句之后，要提交事务；否则事务会回滚
        session.commit();

        System.out.println("影响行数：" + save);
        //需要在添加成功之后，得到刚刚添加数据的主键值
        System.out.println("添加之后：" + user);
    }

    @Test
    public void testEdit(){
        User user = new User();
        user.setId(52);
        user.setUsername("蔡蔡");
        user.setSex("女");
        user.setAddress("广西桂林");
        user.setBirthday(new Date());


        int edit = dao.edit(user);
        //执行了DML操作之后，要提交事务
        session.commit();
        System.out.println("影响行数："+ edit);
    }

    @Test
    public void testDelete(){
        int delete = dao.delete(52);
        session.commit();

        System.out.println("影响行数：" + delete);
    }

    @Test
    public void testFindById(){
        User user = dao.findById(50);
        System.out.println(user);
    }

    @Test
    public void testTotalCount(){
        int totalCount = dao.totalCount();
        System.out.println("总数量：" + totalCount);
    }

    @Test
    public void testSearchByUsername1(){
        List<User> userList = dao.searchByUsername1("%王%");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testSearchByUsername2(){
        List<User> userList = dao.searchByUsername2("王");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testSearchByOrder(){
        List<User> userList = dao.searchByOrder("id");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * Junit提供了@Before注解。
     * 被@Before注解标记的方法，会在当前类里每个@Test方法之前，先被执行一次
     */
    @Before
    public void before() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        session = factory.openSession();
        //ctrl + alt + f：把选中内容提取成  成员变量field
        dao = session.getMapper(UserDao.class);
    }

    /**
     * Junit提供的@After注解
     * 被@After注解标记的方法，会在当前类里每个@Test方法之后，再被执行一次
     */
    @After
    public void after() throws IOException {
        session.close();
        is.close();
    }
}
