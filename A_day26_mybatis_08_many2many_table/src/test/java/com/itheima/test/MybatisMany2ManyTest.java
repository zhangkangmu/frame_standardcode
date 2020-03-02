package com.itheima.test;

import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
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
 * Mybatis多对多关联查询测试类
 * @author zhangyuhong
 * @date 2018/02/29
 */
public class MybatisMany2ManyTest {
    private InputStream is;
    private SqlSession session;
    private UserDao userDao;
    private RoleDao roleDao;

    @Test
    public void testQueryAllUser(){
        List<User> userList = userDao.queryAllUser();
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
        roleDao = session.getMapper(RoleDao.class);
    }

    @After
    public void after() throws IOException {
        session.close();
        is.close();
    }
}
