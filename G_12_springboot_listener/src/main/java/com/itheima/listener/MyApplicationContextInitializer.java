package com.itheima.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 在spring容器刷新之前初始化 调用该接口的方法
 * 用于：做些初始化工作  通常用于web环境，用于激活配置，web上下问的属性注册。
 * 需要配置META-INF/spring.factories 配置之后才能加载调用
 * @author zhangyuhong
 * @description com.itheima.listener
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializer.initialize....");
    }
}
