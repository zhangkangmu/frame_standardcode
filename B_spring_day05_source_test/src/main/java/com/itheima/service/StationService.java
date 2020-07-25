package com.itheima.service;

import com.itheima.event.BaseEvent;
import com.itheima.event.MsgEvent;
import com.itheima.event.StationEvent;
import org.springframework.stereotype.Service;

@Service("stationService")
public class StationService implements EventHandler
{

    public void senMsg(Long userId,String msg) throws InterruptedException {
        //模拟发送站内信耗时
        Thread.sleep(200);
        System.out.println("给用户"+userId+"发送站内信:"+msg);
    }

    @Override
    public void handlerEvent(BaseEvent baseEvent) throws InterruptedException {
        if(baseEvent instanceof StationEvent){
            StationEvent stationEvent = (StationEvent) baseEvent;
            senMsg(stationEvent.getUserId(),stationEvent.getMsg());
        }
    }
}