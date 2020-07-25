/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月06日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public abstract class BaseEvent extends ApplicationEvent {

    public abstract String getFlag();
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public BaseEvent(Object source) {
        super(source);
    }
}