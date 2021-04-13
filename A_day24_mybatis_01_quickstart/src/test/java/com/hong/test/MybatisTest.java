package com.hong.test;

import com.hong.dao.UserDao;
import com.hong.domain.EnumSex;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyuhong
 */
public class MybatisTest {

    private UserDao dao;
    private SqlSession session;
    private InputStream is;

    @Before
    public void testBefore() throws IOException {
        //1. 加载配置文件，得到InputStream
        is = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2. 获取映射器的代理对象。
        //2.1 创建一个构造器，用于构造一个SqlSessionFactory对象。构造者模式：隐藏复杂的构造过程
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //2.2 使用SqlSessionFactory工厂对象，生产一个SqlSession对象。工厂模式：解耦
        session = factory.openSession();
        //SqlSession session1 = new DefaultSqlSession();

        //2.3 通过session，得到一个映射器代理对象（Mybatis使用动态代理，生成了UserDao接口的一个代理对象-实现类）。代理模式
        dao = session.getMapper(UserDao.class);
    }

    @Test
    public void testQuickStart() throws IOException {
        List<User> userList = dao.queryAll();
        for (User user : userList) {
            System.out.println(user);
        }


    }

    //批量插入数据--一条sql
    @Test
    public void testName() throws IOException {

        ArrayList<User> users = new ArrayList<>();
        for (int i = 50; i < 55; i++) {
            User user = new User();
            user.setUserName("zhang"+i);
            user.setAddress("深圳"+1);
            user.setSex(EnumSex.man);
            users.add(user);
        }
       dao.insertUser(users);
        //session默认不会自动提交事务 或者价格true自动提交事务
//        session.commit();
    }

    //有值忽略,没值就插入
    @Test
    public void insertData() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setUserName("zhang"+i);
            user.setAddress("深圳"+1);
            user.setSex(EnumSex.man);
            users.add(user);
        }
        dao.insertDiffData(users);
    }

    //有值就不插入,没有就插入  注意:插入的值必须有一个字段是唯一的,或者有唯一索引
    @Test
    public void insertDiffData() throws IOException {
            User user = new User();
            user.setUserName("zhang");
            user.setAddress("深圳"+1);
            user.setSex(EnumSex.man);
        dao.insertDiffData(user);
    }

    //有值就不更新,没有就插入  注意:插入的值必须有一个字段是唯一的,或者有唯一索引
    @Test
    public void insertDiffAndUpdData() throws IOException {
        User user = new User();
        user.setUserName("zhang");
        user.setAddress("深圳"+1);
        user.setSex(EnumSex.man);
        dao.insertDiffData(user);
    }

    @After
    public void testAfter() throws IOException {
        //3. 释放资源
        session.close();
        is.close();
    }
}
