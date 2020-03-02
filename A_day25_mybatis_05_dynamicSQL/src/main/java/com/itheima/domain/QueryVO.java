package com.itheima.domain;

import lombok.Data;

/**
 * @author zhangyuhong
 * @date 2018/02/28
 */
@Data
public class QueryVO {
    private Integer[] ids;

    private String username;
    private String sex;
}
