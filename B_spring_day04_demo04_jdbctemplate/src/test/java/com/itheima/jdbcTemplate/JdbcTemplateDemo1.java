package com.itheima.jdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @ClassName JdbcTemplateDemo1
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/18 10:48
 * @Version V1.0
 */
public class JdbcTemplateDemo1 {

    // 测试不使用Spring容器，JDBC模板如何使用？
    public static void main(String[] args) {
        // 数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///itcastspring");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        // JdbcTemplate模板
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.update("insert into account(name,money) values (?,?)","冯小刚",2000f);
    }
}
