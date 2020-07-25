/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.domain;

import java.io.Serializable;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年07月29日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
public class Account implements Serializable {
    private Integer id;
    private String name;
    private double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}