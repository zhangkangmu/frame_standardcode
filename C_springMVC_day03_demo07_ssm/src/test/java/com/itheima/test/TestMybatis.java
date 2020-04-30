package com.itheima.test;

import com.itheima.ssm.dao.AccountDao;
import com.itheima.ssm.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName TestMybatis
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/22 9:45
 * @Version V1.0
 */
public class TestMybatis {

    @Test
    public void findAll() throws Exception {
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml123");
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 获取SqlSession（mybatis操作数据库的核心）
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 调用Dao
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        // 操作数据
        List<Account> list = accountDao.findAll();
        for (Account account : list) {
            System.out.println(account);
        }
        // 关闭资源
        sqlSession.close();
        in.close();
    }


    // mybatis默认的提交方式，是手动提交（方便控制事务），后续使用spring的声明式事务处理完成控制
    @Test
    public void save() throws Exception {
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml123");
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 获取SqlSession（mybatis操作数据库的核心）
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 调用Dao
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        // 操作数据
        Account account = new Account();
        account.setName("王五");
        account.setMoney(3000d);
        accountDao.save(account);
        sqlSession.commit(); // 手动提交
        // 关闭资源
        sqlSession.close();
        in.close();
    }
}
