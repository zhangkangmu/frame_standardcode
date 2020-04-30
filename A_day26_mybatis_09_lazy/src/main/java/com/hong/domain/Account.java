package com.hong.domain;

import lombok.Data;

/**
 * 一个帐号，关联一个用户。所在在Account对象里，有一个User的引用
 * @author zhangyuhong
 * @date 2018/02/29
 */
@Data
public class Account {
    private Integer id;
    private Integer uid;
    private Double money;

    private User user;
}
