package com.itheima.service.impl;

import com.itheima.service.AccountService;

import java.util.*;

/**
 * @ClassName AccountServiceImpl
 @Date 2020/3/14 11:09
 * @Version V1.0
 */
public class AccountServiceImpl3 implements AccountService {
    // 集合类型的属性
    // 数组
    Object [] arrays;
    // Set集合
    Set<Object> set;
    // List集合
    List<Object> list;
    // Map集合
    Map<String,Object> map;
    // Properties（类似于Map集合，值只能是字符串）
    Properties prop;

    public void setArrays(Object[] arrays) {
        this.arrays = arrays;
    }

    public void setSet(Set<Object> set) {
        this.set = set;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public void saveAccount() {
        System.out.println("执行AccountServiceImpl3类中的saveAccount方法，arrays="+Arrays.toString(arrays)+"   set="+set+"    list="+list+"    map="+map+"    prop:"+prop);
    }

}
