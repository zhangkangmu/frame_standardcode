package com.itheima;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima
 * @date 2020-4-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudDay1ResttemplateApplicationTests {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRestTemplate() {
        String json = restTemplate.getForObject("http://localhost:18081/user/list", String.class);
        System.out.println(json);
    }
}
