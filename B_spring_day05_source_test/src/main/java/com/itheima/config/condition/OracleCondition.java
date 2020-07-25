/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月11日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class OracleCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "oracle".equals(context.getEnvironment().getProperty("db.name"));
    }
}