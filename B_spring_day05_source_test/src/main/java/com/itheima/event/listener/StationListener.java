package com.itheima.event.listener;

import com.itheima.event.BaseEvent;
import com.itheima.event.StationEvent;
import com.itheima.service.EventHandler;
import com.itheima.service.StationService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StationListener implements ApplicationListener<BaseEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(BaseEvent event) {
        try {
            String serviceName = event.getFlag() + "Service";
            EventHandler eventHandler = applicationContext.getBean(serviceName, EventHandler.class);
            eventHandler.handlerEvent(event);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}