package com.itheima.feign;

import org.springframework.stereotype.Component;

/***
 * 描述
 * @author ljh
 * @packagename com.itheima.feign
 * @version 1.0
 * @date 2020/6/24
 */
@Component
public class SentinelClient1FeignImpl implements SentinelClient1Feign {
    //就是默认兜底的方法
    @Override
    public String hello12() {
        return "feign的调用的时候默认的兜底的方法";
    }
}
