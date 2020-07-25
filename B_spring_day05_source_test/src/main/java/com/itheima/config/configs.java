/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.config;

import com.itheima.domain.ClassRoom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月12日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public interface configs {

    @Bean("classRoom1")
    default public ClassRoom getClassRoom(){
        return new ClassRoom();
    }


}
