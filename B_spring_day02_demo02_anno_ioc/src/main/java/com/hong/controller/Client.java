package com.hong.controller;

import com.hong.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Client
 @Date 2020/3/14 11:10
 * @Version V1.0
 */
public class Client {

    /**
     * 注解方式
     * @param args
     */
//    public static void main(String[] args) {
//        // 使用spring的方式
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        accountService.saveAccount();
//    }


    /**
     * 注解方式
     * 测试单例和多例
     */
//    public static void main(String[] args) {
//        // 使用spring的方式
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        AccountService accountService2 = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        System.out.println(accountService);
//        System.out.println(accountService2);
//    }

    /**
     * 注解方式
     * 测试单例对象的初始化和销毁
     */
    public static void main(String[] args) {
        // 使用spring的方式
        // 容器创建，执行init方法
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
        AccountService accountService2 = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
        System.out.println(accountService);
        System.out.println(accountService2);
        // 容器销毁，执行destory方法
        ac.close();
    }
}
