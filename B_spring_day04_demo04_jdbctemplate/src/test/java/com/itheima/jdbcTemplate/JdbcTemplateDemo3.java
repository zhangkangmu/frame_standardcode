package com.itheima.jdbcTemplate;

import com.itheima.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName JdbcTemplateDemo1
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/18 10:48
 * @Version V1.0
 */
public class JdbcTemplateDemo3 {

    // 使用JdbcTemplate操作数据库的CRUD（非重点）
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        // 新增
//        jdbcTemplate.update("insert into account(name,money) values (?,?)","张艺谋",2000f);
        // 更新
//        jdbcTemplate.update("update account set name=?,money=? where id=?","宋小宝",2500f,4);
        // 删除
//        jdbcTemplate.update("delete from account where id=?",4);
        // 查询所有
//        List<Account> list = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
        // 查询所有（自定义RowMapper，处理字段和实体类的属性不一致的时候）
//        List<Account> list = jdbcTemplate.query("select * from account", new MyRowMapper());
//        System.out.println(list);
        // ID查询
//        Account account = jdbcTemplate.queryForObject("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class),3);
//        System.out.println(account);
        // 条件查询（名称查询）
//        List<Account> list = jdbcTemplate.query("select * from account where name = ?", new BeanPropertyRowMapper<Account>(Account.class), "冯小刚");
//        if(list==null){
//            System.out.println("无记录");
//        }
//        else if (list!=null && list.size()>1){
//            System.out.println("存在多条数据");
//        }
//        else{
//            System.out.println("只有1条数据");
//            System.out.println(list.get(0));
//        }
        // 查询数量
        Long value = jdbcTemplate.queryForObject("select count(*) from account", Long.class);
        System.out.println(value);
    }
}

// 内部类（处理自定义RowMapper，处理字段和实体类的属性不一致的时候）
class MyRowMapper implements RowMapper<Account>{

    // 行映射的方法，处理查询结果的每一行数据，映射成实体类的属性
    @Nullable
    @Override
    public Account mapRow(ResultSet rs, int i) throws SQLException {
        System.out.println("行的索引："+i);// 0表示第一行
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getFloat("money"));
        return account;
    }
}
