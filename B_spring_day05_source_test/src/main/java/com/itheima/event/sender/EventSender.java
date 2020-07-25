package com.itheima.event.sender;

import com.itheima.event.EmailEvent;
import com.itheima.event.MsgEvent;
import com.itheima.event.StationEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EventSender implements ApplicationContextAware {

    //注入当前sender的实例，通过实例调用@Async注解的异步方法，完成异步调用，缩短总的调用时间。
    @Lazy
    @Autowired
    EventSender eventSender;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private ApplicationContext applicationContext;

    //发送注册事件
    public void sendRegister(Long userId, String email, String phone, String msg){
        EmailEvent emailEvent = new EmailEvent(applicationContext,email,msg);
        MsgEvent msgEvent = new MsgEvent(applicationContext,phone,msg);
        StationEvent stationEvent = new StationEvent(applicationContext,userId,msg);
        eventSender.sendEvent(emailEvent);
        eventSender.sendEvent(msgEvent);
        eventSender.sendEvent(stationEvent);
    }

    //给管理员发送邮件和短信通知
    public void sendToManager(String email, String phone, String msg){
        EmailEvent emailEvent = new EmailEvent(applicationContext,email,msg);
        MsgEvent msgEvent = new MsgEvent(applicationContext,phone,msg);
        eventSender.sendEvent(emailEvent);
        eventSender.sendEvent(msgEvent);
    }
	//这里将方法作为异步调用
    @Async
    public void sendEvent(ApplicationEvent event) {
        this.applicationContext.publishEvent(event);
    }


}