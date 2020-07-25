package com.itheima.service;

import com.itheima.event.BaseEvent;
import com.itheima.event.EmailEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("emailService")
public class EmailService implements EventHandler {

    public void senEmail(String email,String msg) throws InterruptedException {
        //模拟发送邮件耗时
        Thread.sleep(300);
        System.out.println("给邮箱"+email+"发送邮件:"+msg);
    }

    @Override
    public void handlerEvent(BaseEvent baseEvent) throws InterruptedException {
        if(baseEvent instanceof EmailEvent){
            EmailEvent emailEvent = (EmailEvent) baseEvent;
            senEmail(emailEvent.getEmail(),emailEvent.getContext());
        }
    }
}