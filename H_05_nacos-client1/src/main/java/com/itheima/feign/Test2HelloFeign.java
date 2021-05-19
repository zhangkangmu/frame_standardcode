package com.itheima.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/***
 * 描述
 * @author ljh
 * @packagename com.itheima.feign
 * @version 1.0
 * @date 2020/6/23
 */
@FeignClient(name="nacos-client2")
public interface Test2HelloFeign {
    //调用9002的服务的接口
    @GetMapping("/test2hello")
    public String hello();

}
