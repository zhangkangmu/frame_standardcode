/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.service;

import com.itheima.event.BaseEvent;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年12月06日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public interface EventHandler {

    public void handlerEvent(BaseEvent baseEvent) throws InterruptedException;
}