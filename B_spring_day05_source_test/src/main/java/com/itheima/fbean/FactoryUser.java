/**
 * Copyright (c) 2020 itheima.com, All rights reserved.
 *
 * @Author: lvyang
 */
package com.itheima.fbean;

import com.itheima.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2020年06月17日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class FactoryUser implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}