package com.itheima.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * SpringApplication run方法的监听器，当我们使用SpringApplication调用Run方法的时候触发该监听器回调方法。
 * 需要配置META/spring.factories 配置之后才能加载调用
 * @author Steven
 * @description com.itheima.listener
 */
public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    /**
     * 实现SpringApplicationRunListener接口,
     * 必须提供一个构造函数(SpringApplication application, String[] args)
     * @param application
     * @param args
     */
    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
    }

    //1.
    @Override
    public void starting() {
        System.out.println("starting...项目启动中");
    }

    //2.
    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("environmentPrepared...环境对象准备");
    }
    //3.MyApplicationContextInitializer.initialize  初始化配置属性
    //4.
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("contextPrepared...上下文对象准备");
    }
    //5.
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("contextLoaded...上下文对象开始加载");
    }
    //6.
    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("started...上下文对象加载完成");
    }
    //7.MyApplicationRunner.run
    //8.MyCommandLineRunner.run
    //9.
    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("running...项目启动完成，开始运行");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("failed...项目启动失败！");
    }
}
