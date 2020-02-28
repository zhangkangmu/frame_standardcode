package com.itheima.domain;

import lombok.Data;

import java.util.Date;

/**
 * Data注解：添加了get/set，tostring，equals...
 * @author liuyp
 * @date 2020/02/27
 */
@Data
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
}
