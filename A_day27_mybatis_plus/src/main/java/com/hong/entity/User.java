package com.hong.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created by zhangyuhong
 * Date:2020/4/30
 */
@Data
@TableName(value = "user_demo")
public class User {
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @Version  //乐观锁用的,实际上就是更新的时候加上版本号
    //eg: UPDATE USER SET age =11,VERSION=VERSION+1 WHERE id=1 AND VERSION=1
    private Integer version;
    @TableLogic
    private Integer deleted;
}
