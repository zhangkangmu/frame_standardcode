/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月06日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class MyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    static String printStr = "order:%s:执行了:%s";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println(String.format(printStr,getOrder(),"postProcessBeanDefinitionRegistry..."));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(String.format(printStr,getOrder(),"postProcessBeanFactory..."));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}