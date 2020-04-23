package com.itheima.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String address;
    private Integer age;
    public User() {
    }
    public User(String name, String address, Integer age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
    //..get set toString 略


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
