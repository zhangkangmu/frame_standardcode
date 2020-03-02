package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.QueryVO;
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
    public void testEdit(){
        User user = new User();
        user.setId(53);
        user.setUsername("蔡蔡");
        user.setSex("女");
        user.setAddress("广西桂林");
        user.setBirthday(new Date());


        int edit = dao.edit(user);
        session.commit();
        System.out.println("影响行数："+ edit);
    }

    @Test
    public void testDelete(){
        int delete = dao.delete(51);

        //如果session对象是通过 factory.openSession(true)，得到的，那么不需要再手动提交事务了，会自动提交
        //session.commit();

        System.out.println("影响行数：" + delete);
    }

    @Test
    public void testTotalCount(){
        int totalCount = dao.totalCount();
        System.out.println("总数量：" + totalCount);
    }

    @Test
    public void testSearchByVo(){
        User user = new User();
        user.setUsername("%王%");

        QueryVO vo = new QueryVO();
        vo.setUser(user);

        List<User> userList = dao.searchByVo(vo);
        for (User u : userList) {
            System.out.println(u);
        }
    }

    @Test
    public void testQueryAllUser2(){
        List<User2> user2List = dao.queryAllUser2();
        for (User2 user2 : user2List) {
            System.out.println(user2);
        }
    }

    @Test
    public void testSearchByUsername(){
        List<User> userList = dao.searchByUsername("%王%");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Before
    public void before() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //session = factory.openSession();
        session = factory.openSession(true); //执行完SQL语句，会自动提交事务，不需要再手动提交了
        dao = session.getMapper(UserDao.class);
    }

    @After
    public void after() throws IOException {
        session.close();
        is.close();
    }
}
