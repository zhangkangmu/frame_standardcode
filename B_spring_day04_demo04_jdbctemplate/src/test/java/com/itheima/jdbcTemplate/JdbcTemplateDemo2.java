package com.itheima.jdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName JdbcTemplateDemo1
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/18 10:48
 * @Version V1.0
 */
public class JdbcTemplateDemo2 {

    // 测试使用Spring容器，JDBC模板如何使用？
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        jdbcTemplate.update("insert into account(name,money) values (?,?)","张艺谋",2000f);
    }
}
