/**
 * Copyright (c) 2020 itheima.com, All rights reserved.
 *
 * @Author: lvyang
 */
package com.itheima.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.PriorityOrdered;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2020年06月17日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class BeanFactoryPostPorcesserTest implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setFactoryBeanName("userFactory");
        rootBeanDefinition.setFactoryMethodName("getUser");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "哪吒");
        MutablePropertyValues propertyValues = new MutablePropertyValues(map);
        rootBeanDefinition.setPropertyValues(propertyValues);
        registry.registerBeanDefinition("user10",rootBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}