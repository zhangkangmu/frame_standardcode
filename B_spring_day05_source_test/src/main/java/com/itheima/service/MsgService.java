package com.itheima.service;

import com.itheima.event.BaseEvent;
import com.itheima.event.EmailEvent;
import com.itheima.event.MsgEvent;
import org.springframework.stereotype.Service;

@Service("msgService")
public class MsgService implements EventHandler {
    public void senMsg(String Phone,String msg) throws InterruptedException {
        //模拟发送短信耗时
        Thread.sleep(400);
        System.out.println("给手机"+Phone+"发送短信息:"+msg);
    }

    @Override
    public void handlerEvent(BaseEvent baseEvent) throws InterruptedException {
        if(baseEvent instanceof MsgEvent){
            MsgEvent msgEvent = (MsgEvent) baseEvent;
            senMsg(msgEvent.getPhone(),msgEvent.getMsg());
        }
    }
}