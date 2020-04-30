package com.hong.dao.impl;

import com.hong.dao.AccountDao;
import com.hong.domain.Account;
import com.hong.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName AccountDaoImpl
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/15 14:21
 * @Version V1.0
 */
public class AccountDaoImpl implements AccountDao {

    QueryRunner queryRunner;

    ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    public void save(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(),"insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(),"update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(),"delete from account where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account findById(Integer id) {
        Account account = null;
        try {
            account = queryRunner.query(connectionUtils.getThreadConnection(),"select * from account where id=?", new BeanHandler<Account>(Account.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public List<Account> findAll() {
        List<Account> list = null;
        try {
            list = queryRunner.query(connectionUtils.getThreadConnection(),"select * from account", new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 根据账号名称查询账号
    public Account findAccountByName(String name) {
        Account account = null;
        try {
            List<Account> list = queryRunner.query(connectionUtils.getThreadConnection(),"select * from account where name = ?", new BeanListHandler<Account>(Account.class), name);
            if(list==null || list.size()==0){
                return null;
            }
            // 查询多个值
            if(list.size()>1){
                throw new RuntimeException("查询结果集不能大于1");
            }
            return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
