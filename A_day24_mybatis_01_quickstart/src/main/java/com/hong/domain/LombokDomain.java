package com.hong.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by zhangyuhong
 * Date:2018/2/27
 * lombok的使用方式
 */
//@Setter //生成set方法
//@Getter //生成get方法
@ToString(exclude = "uid") //生成toString方法，排队掉uid字段
@AllArgsConstructor //生成全参构造器
@NoArgsConstructor  //生成无参构造器
public class LombokDomain {
    private Integer uid;
    private String name;
    private String sex;
    private Date birthday;
    private String email;

    public LombokDomain(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
