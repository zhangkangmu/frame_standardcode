package com.itheima.mymvc1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用在业务控制器的方法上，用于声明 方法的映射路径。
 * 可以用在方法上
 * 保留到运行阶段（在核心控制器里要通过反射获取注解的值，反射是在运行阶段产生作用的，所以注解就必须要保留到运行阶段）
 *      Retention如果是源码阶段：在Java里有，编译成class文件就丢弃了注解
 *      Retention如果是字节码阶段：在Java里有，编译的class文件里有，但是加载到JVM会丢弃掉
 *      Retention如果是运行阶段：在Java里有，编译的class文件里有，加载到JVM里也有
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     * 配置的映射路径
     */
    String value();
}
