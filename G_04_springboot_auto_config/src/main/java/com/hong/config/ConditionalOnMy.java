package com.hong.config;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 自定义注解实现创建建bean逻辑包装
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.config
 * @date 2020-4-22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnClassCondition.class)
public @interface ConditionalOnMy {
    //注解的属性-加载的类名
    String[] name();
}
