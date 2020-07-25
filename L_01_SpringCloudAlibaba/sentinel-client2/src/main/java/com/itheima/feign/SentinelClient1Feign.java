package com.itheima.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/***
 * 描述
 * @author ljh
 * @packagename com.itheima.feign
 * @version 1.0
 * @date 2020/6/24
 */
@FeignClient(name="sentinel-client1",fallback = SentinelClient1FeignImpl.class)
public interface SentinelClient1Feign {
    @GetMapping("/hello12")
    public String hello12();
}
