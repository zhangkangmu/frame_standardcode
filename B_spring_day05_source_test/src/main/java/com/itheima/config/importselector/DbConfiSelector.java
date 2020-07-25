/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.config.importselector;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月11日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class DbConfiSelector implements ImportSelector, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String property = environment.getProperty("db.name");
        return new String[]{environment.getProperty("db."+property)};
    }
}