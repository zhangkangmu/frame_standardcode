package com.hong.controller;

import com.hong.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Client
 * @Description TODO
 * @Date 2020/3/14 11:10
 * @Version V1.0
 */
public class Client {

    /**
     * l 第一种：采用无参数的构造方法方式实例化（用的最多）
     */
//    public static void main(String[] args) {
//        // 解耦
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        accountService.saveAccount();
//    }

    /**
     * l 第二种：采用静态工厂实例化的方式
     */
//    public static void main(String[] args) {
//        // 正常调用
//        // AccountService accountService = StaticFactroy.createObject();
//        // 使用spring的方式
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        accountService.saveAccount();
//    }

    /**
     * 第三种：采用实例工厂（非静态的）实例化的方式
     */
//    public static void main(String[] args) {
//        // 正常调用
////        InstanceFactroy instanceFactroy = new InstanceFactroy();
////        AccountService accountService = instanceFactroy.createObject();
//        // 使用spring的方式
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        accountService.saveAccount();
//    }

    /**
     * 4.4 Bean的作用域配置：scope的配置
     * Spring创建这个类的时候，默认采用的单例的模式进行创建的。如果想去改变单例模式，需要通过scope进行配置。
     Scope属性中有以下几个取值:
     - singleton：默认值，单例的。
     - prototype：多例的。
     */
//    public static void main(String[] args) {
//        // 使用spring的方式
         // ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        AccountService accountService2 = ac.getBean("accountService", AccountService.class);
//        System.out.println(accountService);
//        System.out.println(accountService2);
//    }


    /**
     * 4.5 Bean的生命周期的配置（了解）
     */
    public static void main(String[] args) {
        // 使用spring的方式
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
        AccountService accountService2 = ac.getBean("accountService", AccountService.class);
        System.out.println(accountService);
        System.out.println(accountService2);
        // 容器销毁
        ac.close();
    }
}
