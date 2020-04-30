package com.itheima.controller;

import com.itheima.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Client
 @Date 2020/3/14 11:10
 * @Version V1.0
 */
public class Client {

    /**
     * 5.2 构造函数注入 （了解）
     */
//    public static void main(String[] args) {
//        // 使用spring的方式
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        accountService.saveAccount();
//    }

    /**
     * 5.3 set方法注入 （推荐使用）
     * 5.4 使用p名称空间注入数据（本质还是调用set方法） （了解）
     */
//    public static void main(String[] args) {
//        // 使用spring的方式
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService accountService = ac.getBean("accountService2", AccountService.class); // 底层：来自Map集合
//        accountService.saveAccount();
//    }

    /**
     * 5.5 注入集合属性（复杂类型）
     */
//    public static void main(String[] args) {
//        // 使用spring的方式
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        AccountService accountService = ac.getBean("accountService3", AccountService.class); // 底层：来自Map集合
//        accountService.saveAccount();
//    }


    /**
     * 5.6 在Service中，注入Dao
     * @param args
     */
    public static void main(String[] args) {
        // 使用spring的方式
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
        accountService.saveAccount();
    }
}
