package com.itheima.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 在容器准备好了之后可以回调
 * @componet修饰即可
 * @author Steven
 * @description com.itheima.listener
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        //可以通过idea的springboot启动配置传入:Environment -> Program arguments
        System.out.println("main函数的入参:" + Arrays.asList(args));
        System.out.println("CommandLineRunner.run...");
    }
}
