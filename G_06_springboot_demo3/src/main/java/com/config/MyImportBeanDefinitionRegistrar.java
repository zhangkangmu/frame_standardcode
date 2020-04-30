package com.config;

import com.hong.Role;
import com.hong.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现ImportBeanDefinitionRegistrar接口把javabean添加到spirng容器中
 * @author zhangyuhong
 * @version 1.0
 * @description com.config
 * @date 2020-4-22
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 实现注册bean
     * @param importingClassMetadata 注解元数据 获取注解属性的
     * @param registry bean注册器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //注册用户对象
        AbstractBeanDefinition userDefinition = BeanDefinitionBuilder.rootBeanDefinition(User.class).getBeanDefinition();
        //registerBeanDefinition("容器id",注册的对象)
        registry.registerBeanDefinition("user", userDefinition);

        //注册用户对象
        AbstractBeanDefinition roleDefinition = BeanDefinitionBuilder.rootBeanDefinition(Role.class).getBeanDefinition();
        //registerBeanDefinition("容器id",注册的对象)
        registry.registerBeanDefinition("role", roleDefinition);
    }
}
