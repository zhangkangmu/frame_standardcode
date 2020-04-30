package com.itheima.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.Map;

/**
 * Spring的判断实现
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.config
 * @date 2020-4-22
 */
public class OnClassCondition implements Condition {
    /**
     * 实现判断有没依赖某个jar包
     * @param context Spring上下文对象 bean工厂、环境信息、资源信息
     * @param metadata 注解元对象 用于获取注解的属性内容
     * @return 判断结果 true 表示通过 false表示没有依赖(不通过)
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //判断项目中有没有redisclient依赖，有的话返回true
        //加载这个类，没报错，说明有这个jar包
        try {
            //redis.clients.jedis.Jedis
            //Class.forName("redis.clients.jedis.Jedis");

            //获取注解的属性
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnMy.class.getName());
            //[redis.clients.jedis.Jedis, json, spring]
            String[] names = (String[]) annotationAttributes.get("name");
            System.out.println(Arrays.asList(names));
            for (String name : names) {
                //加载某个，是否存在，不存在会报错-ClassNotFoundException
                Class.forName(name);
            }
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //没有返回false
        return false;
    }
}
