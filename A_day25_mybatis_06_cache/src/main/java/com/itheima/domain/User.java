package com.itheima.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Data注解：添加了get/set，tostring，equals...
 * @author zhangyuhong
 * @date 2018/02/27
 */
@Getter
@Setter
public class User implements Serializable {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
}
