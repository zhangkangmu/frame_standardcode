package com.itheima.test;

import com.itheima.event.sender.EventSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MyTest {

    @Autowired
    EventSender eventSender;

    @Test
    public void register() throws InterruptedException {
        long l = System.currentTimeMillis();
        Long userId = 13l;
        String email = "123123@qq.com";
        String phone = "12345678912";
        String msg = "注册成功";
        eventSender.sendRegister(userId,email,phone,msg);
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
        Thread.sleep(1000);
    }
}