package com.hong.domain;

import lombok.Data;

/**
 * 查询帐号信息 和 用户信息，封装到这个JavaBean里
 * @author zhangyuhong
 * @date 2018/02/28
 * 单对单结果集查询后的封装
 */
@Data
public class UserAccount extends Account{

    //用户的信息

    private String username;
    private String sex;

    //帐号的信息。Account对象里已经定义过这些字段了

    /*private Integer id;
    private Integer uid;
    private Double money;*/

    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                "} " + super.toString();
    }
}
