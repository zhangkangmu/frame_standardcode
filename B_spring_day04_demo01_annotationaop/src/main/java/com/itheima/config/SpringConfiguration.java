package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName SpringConfiguration
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/18 9:37
 * @Version V1.0
 */
@Configuration // 取代applicationContext.xml
@ComponentScan(value = "com.itheima")
@EnableAspectJAutoProxy
public class SpringConfiguration {


}
