/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.domain;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月09日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Data
public class User implements InitializingBean {
    private String name;
    private String sex;

    public void init(){
        System.out.println("user被初始化了--init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("user被初始化了");
    }
}