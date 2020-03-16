package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName AccountDaoImpl
 *
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/15 14:21
 * @Version V1.0
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    QueryRunner queryRunner;

    public void save(Account account) {
        try {
            queryRunner.update("insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Account account) {
        try {
            queryRunner.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            queryRunner.update("delete from account where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account findById(Integer id) {
        Account account = null;
        try {
            account = queryRunner.query("select * from account where id=?", new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public List<Account> findAll() {
        List<Account> list = null;
        try {
            list = queryRunner.query("select * from account", new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
