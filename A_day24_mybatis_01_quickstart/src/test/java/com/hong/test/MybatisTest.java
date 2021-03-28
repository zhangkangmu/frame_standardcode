package com.hong.test;

import com.hong.dao.UserDao;
import com.hong.domain.EnumSex;
import com.hong.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zhangyuhong
 * @date 2018/02/27
 */
public class MybatisTest {

    @Test
    public void testQuickStart() throws IOException {
        //1. 加载配置文件，得到InputStream
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2. 获取映射器的代理对象。
        //2.1 创建一个构造器，用于构造一个SqlSessionFactory对象。构造者模式：隐藏复杂的构造过程
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //2.2 使用SqlSessionFactory工厂对象，生产一个SqlSession对象。工厂模式：解耦
        SqlSession session = factory.openSession();
        //SqlSession session1 = new DefaultSqlSession();

        //2.3 通过session，得到一个映射器代理对象（Mybatis使用动态代理，生成了UserDao接口的一个代理对象-实现类）。代理模式
        UserDao dao = session.getMapper(UserDao.class);

        List<User> userList = dao.queryAll();
        for (User user : userList) {
            System.out.println(user);
        }

        //3. 释放资源
        session.close();
        is.close();
    }
}
