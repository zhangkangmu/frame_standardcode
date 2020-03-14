package com.itheima.controller;

import com.itheima.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Client
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/14 11:10
 * @Version V1.0
 */
public class Client {

    //IOC的入门案例
//    public static void main(String[] args) {
//        // 耦合
//        // AccountService accountService = new AccountServiceImpl();
//        // 解耦
//        // Spring的工厂（ApplicationContext接口）加载applicationContext.xml
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        // 调用方式，按照名称匹配对象（对应 bean id）
//        // AccountService accountService = (AccountService)ac.getBean("accountService");
//        // 调用方式 ，按照类型匹配对象（对应 bean class）（注意：在spring容器中，如果出现一个对象被创建多次怎么办？按照类型就会有问题，这个时候只能按照名称匹配）
//        // AccountService accountService = ac.getBean(AccountService.class);
//        // 调用方式，按照名称和类型匹配（对应 bean id class）
//        AccountService accountService = ac.getBean("accountService", AccountService.class);
//        accountService.saveAccount();
//    }

    /**
     * 【BeanFactory 和ApplicationContext 的区别 】（了解）
     * BeanFactory 才是Spring 容器中的顶层接口。
         ApplicationContext 是它的子接口。
         BeanFactory 和ApplicationContext 的区别：
         创建的方式都表示单例对象。
         创建对象的时间点不一样。
         ApplicationContext：只要一读取配置文件，默认情况下就会创建对象。（立即加载）
         BeanFactory：什么时候使用什么时候创建对象。（延迟加载）
     */
//    public static void main(String[] args) {
//        // 解耦
//        // Spring的工厂（ApplicationContext接口）加载applicationContext.xml
////        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
////        AccountService accountService1 = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
////        AccountService accountService2 = ac.getBean("accountService", AccountService.class);
////        System.out.println(accountService1);
////        System.out.println(accountService2);
//        // Spring的工厂（BeanFactory接口）加载applicationContext.xml
//        BeanFactory ac = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
//        AccountService accountService1 = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        AccountService accountService2 = ac.getBean("accountService", AccountService.class);
//        System.out.println(accountService1);
//        System.out.println(accountService2);
//    }

    /**
     * 【ApplicationContext 接口的实现类 】
     * （1）ClassPathXmlApplicationContext： （重点）
     它是从类的根路径下加载配置文件 推荐使用这种
     （2）FileSystemXmlApplicationContext：
     它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置。
     （3）AnnotationConfigApplicationContext: （第2天讲）
     当我们使用注解配置容器对象时，需要使用此类来创建spring 容器。它用来读取注解。
     */
//    public static void main(String[] args) {
//        // 解耦
//        // Spring的工厂（ApplicationContext接口ClassPathXmlApplicationContext实现类）加载applicationContext.xml
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        // Spring的工厂（ApplicationContext接口FileSystemXmlApplicationContext实现类）加载applicationContext.xml（用的不多）
//        // ApplicationContext ac = new FileSystemXmlApplicationContext("d:/applicationContext.xml");
//        AccountService accountService1 = ac.getBean("accountService", AccountService.class); // 底层：来自Map集合
//        AccountService accountService2 = ac.getBean("accountService", AccountService.class);
//        System.out.println(accountService1);
//        System.out.println(accountService2);
//
//    }

    /**
     * 4.2 id和name的配置（了解）
     id中不能出现特殊字符（容器中的唯一标识），name中可以出现特殊的字符（表示引用）。\
     id="accountService,accountService1,accountService2"：accountService,accountService1,accountService2表示唯一标识
     name="accountService,accountService1,accountService2"：使用逗分隔，name是可以解析特殊字符串，有3个名称可以找到spring所创建的对象的
     */
    public static void main(String[] args) {
        // 解耦
        // Spring的工厂（ApplicationContext接口ClassPathXmlApplicationContext实现类）加载applicationContext.xml
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ac.getBean("accountService1", AccountService.class); // 底层：来自Map集合
        System.out.println(accountService);
    }
}
