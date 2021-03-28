package com.hong.domain;

import lombok.Data;

import java.util.Date;

/**
 * Data注解：添加了get/set，tostring，equals...
 * @author zhangyuhong
 * @date 2018/02/27
 */
@Data
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private EnumSex sex;
    private String address;
}
