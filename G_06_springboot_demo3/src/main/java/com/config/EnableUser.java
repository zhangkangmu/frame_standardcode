package com.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Steven
 * @version 1.0
 * @description com.config
 * @date 2020-4-22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(UserConfig.class)
public @interface EnableUser {
}
