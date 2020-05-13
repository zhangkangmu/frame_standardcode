package com.hong.pojo;

import java.io.Serializable;

/***
 * 描述
 * @author ljh
 * @packagename com.itheima.pojo
 * @version 1.0
 * @date 2020/5/13
 */
public class Person implements Serializable {
    private Long id;
    private String name;

    public Person() {
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
