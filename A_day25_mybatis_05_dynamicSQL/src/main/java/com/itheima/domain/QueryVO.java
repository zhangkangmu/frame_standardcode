package com.itheima.domain;

import lombok.Data;

/**
 * @author liuyp
 * @date 2020/02/28
 */
@Data
public class QueryVO {
    private Integer[] ids;

    private String username;
    private String sex;
}
