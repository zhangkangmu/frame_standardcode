package com.hong.dao.impl;

import com.hong.dao.AccountDao;
import com.hong.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * @ClassName AccountDaoImpl
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/18 11:34
 * @Version V1.0
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {


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
