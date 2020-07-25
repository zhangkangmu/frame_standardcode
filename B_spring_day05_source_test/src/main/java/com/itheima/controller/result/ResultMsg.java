/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.controller.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年07月30日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Data
public class ResultMsg<T> implements Serializable {
    private T data;
    private Boolean success=true;
    private String message;

    public void fail(String message){
        this.setSuccess(false);
        this.setMessage(message);
    }
}