package com.hong.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Data注解：添加了get/set，tostring，equals...
 * @author zhangyuhong
 * @date 2018/02/27
 */
@Data
public class User2 implements Serializable {
    private Integer userId;
    private String username;
    private Date userBirthday;
    private String userSex;
    private String userAddress;
}
