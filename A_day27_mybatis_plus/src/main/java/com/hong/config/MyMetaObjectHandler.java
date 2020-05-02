package com.hong.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhangyuhong
 * Date:2020/5/2
 */
@Slf4j
@Component   //要加入spring的容器
//配置更新和新增的日期自动填充
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * @param metaObject 元数据
     * 插入的时候自动插入和更新日期
     * 前提是entity对象里的属性配置了  @TableField(fill = FieldFill.INSERT)
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
