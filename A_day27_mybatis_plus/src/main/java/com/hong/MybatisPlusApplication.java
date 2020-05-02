package com.hong;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zhangyuhong
 * Date:2020/4/30
 */
@SpringBootApplication
@MapperScan("com.hong.mapper")
public class MybatisPlusApplication {
    public static void main(String[] args) {

    }
}
