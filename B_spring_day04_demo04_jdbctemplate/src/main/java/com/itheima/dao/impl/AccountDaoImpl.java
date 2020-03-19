package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * @ClassName AccountDaoImpl
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/18 11:34
 * @Version V1.0
 */
//JdbcDaoSupport,为了使用jdbctemplate模板
    //JdbcDaoSupport里有一个属性:jdbcTemplate
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {


    // ID查询
    @Override
    public Account findById(Integer id) {
        //因为有了JdbcDaoSupport,所以,可以直接使用getJdbcTemplate()
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
