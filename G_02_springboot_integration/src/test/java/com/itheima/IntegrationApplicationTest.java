package com.itheima;

import com.itheima.integration.pojo.User;
import com.itheima.integration.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima  一定要跟启动引导类在同一目录或者子目录下
 * @date 2020-4-21
 */
@RunWith(SpringRunner.class)
//启动SpringBoot项目
@SpringBootTest
public class IntegrationApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindAll() {
        List<User> all = userService.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
}
