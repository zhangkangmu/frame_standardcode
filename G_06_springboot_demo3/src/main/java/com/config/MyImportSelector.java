package com.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现ImportSelector完成把javabean导入到spring容器中
 * @author Steven
 * @version 1.0
 * @description com.config
 * @date 2020-4-22
 */
public class MyImportSelector implements ImportSelector{
    /**
     * @param importingClassMetadata  注解元数据 获取注解的属性
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //返回类的全路径名，有几个就创建几个bean
        return new String[]{"com.itheima.User","com.itheima.Role"};
    }
}
