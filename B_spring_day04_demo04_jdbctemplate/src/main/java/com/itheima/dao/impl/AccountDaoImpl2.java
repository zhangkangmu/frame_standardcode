package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * @ClassName AccountDaoImpl
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/18 11:34
 * @Version V1.0
 */
@Repository("accountDao")
public class AccountDaoImpl2 extends JdbcDaoSupport implements AccountDao {

    @Autowired // 可以放置到属性上，也可以放置到set方法上，通过set方法，找到JdbcDaoSupport中的setDataSource(DataSource dataSource)
    public void setDi(DataSource dataSource){
        System.out.println("注入数据源："+dataSource);
        super.setDataSource(dataSource);
    }

    // ID查询
    @Override
    public Account findById(Integer id) {
        Account account = super.getJdbcTemplate().queryForObject("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class),id);
        return account;
    }

    @Override
    public Account findByName(String name) {
        List<Account> list = super.getJdbcTemplate().query("select * from account where name = ?", new BeanPropertyRowMapper<Account>(Account.class), name);
        if(list==null){
            return null;
        }
        else if (list!=null && list.size()>1){
            throw new RuntimeException("结果集不能大于1");
        }
        return list.get(0);
    }

    @Override
    public void update(Account account) {
        super.getJdbcTemplate().update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
    }
}
