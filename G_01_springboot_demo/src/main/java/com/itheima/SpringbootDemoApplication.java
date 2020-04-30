package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * SpringBoot起步引导类
 * @author zhangyuhong
 * @version 1.0
 * @date 2020-4-21
 */
//标识当前类是SpringBoot启动
@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        //run(要加载的类字节码，main函数入参)
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
