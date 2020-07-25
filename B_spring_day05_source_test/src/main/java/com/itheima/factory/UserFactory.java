/**
 * Copyright (c) 2020 itheima.com, All rights reserved.
 *
 * @Author: lvyang
 */
package com.itheima.factory;

import com.itheima.domain.User;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2020年06月17日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class UserFactory {

    public User getUser(){
        return new User();
    }
}