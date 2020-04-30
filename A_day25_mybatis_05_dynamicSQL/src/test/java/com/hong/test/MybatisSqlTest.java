package com.hong.test;

import com.hong.dao.UserDao;
import com.hong.domain.QueryVO;
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
 * @author zhangyuhong
 * @date 2018/02/28
 */
public class MybatisSqlTest {

    private UserDao userDao;
    private InputStream is;
    private SqlSession session;

    @Test
    public void testSearch(){
        User user = new User();
        user.setUsername("%王%");
        user.setSex("男");

        List<User> userList = userDao.search(user);
        for (User u : userList) {
            System.out.println(u);
        }
    }

    @Test
    public void testSearchForEach(){
        QueryVO vo = new QueryVO();
        vo.setIds(new Integer[]{41,42,43,44,45});
        //vo.setIds(new Integer[]{});

        List<User> userList = userDao.searchForeach(vo);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testSearch2(){
        QueryVO vo = new QueryVO();
        vo.setUsername("%王%");
        vo.setSex("男");
        vo.setIds(new Integer[]{41,42,43,44,45});


        List<User> userList = userDao.search2(vo);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Before
    public void before() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
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
