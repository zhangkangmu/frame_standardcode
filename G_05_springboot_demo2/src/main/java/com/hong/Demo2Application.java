package com.hong;

import com.config.MyImportBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong
 * @date 2020-4-22
 */
@SpringBootApplication
//1.第一种使用组件扫描 扫描包路径放大
//@ComponentScan(basePackages = "com")
//2.第二种使用import注解进行导入配置类的方式即可
//@Import(UserConfig.class)
//3.自定注解，包装所有要导入的配置
//@EnableUser

//================@Import注解使用的四种方式===================
//	直接导入Bean --需要我们javabean加入了@Component
//@Import(User.class)
//	导入配置类
//@Import(UserConfig.class)
//	导入ImportSelector的实现类，通常用于加载配置文件中的Bean
//@Import(MyImportSelector.class)
//	导入ImportBeanDefinitionRegistrar实现类，代码注册
@Import(MyImportBeanDefinitionRegistrar.class)
public class Demo2Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Demo2Application.class, args);

        //Object user = context.getBean("user");
        //System.out.println(user);

        //全路径获取-不使用
        //Object user = context.getBean("com.hong.User");
        //System.out.println(user);
        //Object role = context.getBean("com.hong.Role");
        //System.out.println(role);

        //类型装配
        Object user = context.getBean(User.class);
        System.out.println(user);
        Object role = context.getBean(Role.class);
        System.out.println(role);
    }
}
