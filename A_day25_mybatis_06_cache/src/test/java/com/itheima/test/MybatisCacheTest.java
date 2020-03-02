package com.itheima.test;

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

/**
 * Mybatis的缓存测试
 * @author zhangyuhong
 * @date 2018/02/28
 */
public class MybatisCacheTest {
    private UserDao userDao;
    private InputStream is;
    private SqlSession session;
    private SqlSessionFactory factory;

    @Test
    public void testFindById(){
        User user = userDao.findById(41);
        System.out.println(user);
    }

    /**
     * 一级缓存：SqlSession提供的
     *  SqlSession第一次查询数据，会把查询到对象存储到SqlSession的缓存区域
     *  SqlSession第二次查询相同数据，会优先从缓存区域里查找
     *
     * 验证一级缓存：
     *  1. 使用SqlSession对象获取映射器userDao1对象
     *     使用userDao1查询id为41的用户，得到User对象：从数据库里查询
     *
     *  2. 使用SqlSession对象获取映射器userDao2对象
     *     使用userDao2查询id为41的用户，得到User对象：从缓存里得到，不会再查询数据库了
     *
     *  SQL语句执行几次？一次
     *  再次得到的User对象地址是否相同？相同
     */
    @Test
    public void testLevel1Cache(){
        //1. 使用SqlSession对象获取映射器userDao1对象
        UserDao userDao1 = session.getMapper(UserDao.class);
        User user1 = userDao1.findById(41);
        System.out.println(user1);

        //清除一级缓存：当SqlSession执行了添加、修改、删除、commit()、close()、clearCache()，会清除一级缓存
        session.clearCache();

        //2. 使用SqlSession对象获取映射器userDao2对象
        UserDao userDao2 = session.getMapper(UserDao.class);
        User user2 = userDao2.findById(41);
        System.out.println(user2);
    }

    /**
     * 测试二级缓存：是SqlSessionFactory提供的缓存区域
     *      同类型映射器接口，可以共享缓存
     *
     * 验证二级缓存：
     *  1. 用factory得到SqlSession对象：session1
     *     用session1，得到UserDao对象：userDao1
     *     用userDao1查询id为41的用户：
     *                  如果二级缓存有效，这个结果会被缓存到SqlSessionFactory里
     *                  哪怕是session1对象关闭了，缓存的数据仍然存在
     *
     *     把session1关闭掉
     *
     *  2. 用factory得到SqlSession对象：session2
     *     用session2，得到userDao对象：userDao2
     *     用userDao2查询id为41的用户
     *                  因为SqlSessionFactory里缓存的id为41的用户
     *                  这次查询应该是从二级缓存里查询得到的
     *
     *     把session2关闭掉
     *
     *  SQL语句执行几次？一次
     *  两次得到的User对象地址是否相同？
     */
    @Test
    public void testLevel2Cache(){
        //1.用factory得到SqlSession对象：session1
        SqlSession session1 = factory.openSession();
        UserDao userDao1 = session1.getMapper(UserDao.class);
        User user1 = userDao1.findById(41); //数据会被缓存到工厂SqlSessionFactory里。
        System.out.println(user1);
        session1.close();

        //2.用factory得到SqlSession对象：session2
        SqlSession session2 = factory.openSession();
        UserDao userDao2 = session2.getMapper(UserDao.class);
        User user2 = userDao2.findById(41); //会从SqlSessionFactory工厂里获取缓存的数据
        System.out.println(user2);
        session2.close();
    }

    @Before
    public void before() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(is);
        //ctrl + alt + f，把选中内容提取成为field 成员变量
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void after() throws IOException {
        session.close();
        is.close();
    }
}
