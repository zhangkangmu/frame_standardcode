package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Repository // 加不加都行
public interface AccountDao {

    // 方法
    // 查询所有
    @Select(value = "select * from account")
    public List<Account> findAll();
    // 保存
    @Insert(value = "insert into account(name,money) values (#{name},#{money})")
    public void save(Account account);
}
