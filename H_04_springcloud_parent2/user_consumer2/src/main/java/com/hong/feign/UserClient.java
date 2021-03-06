package com.hong.feign;

import com.hong.domain.User;
import com.hong.feign.conf.FeignConfig;
import com.hong.feign.fallback.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息Feign客户端接口
 * 主要是包装生产者请地址、输入与出输
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.feign
 * @date 2020-4-25
 */
//1.绑定生产者的服务名称(Eureka)
@FeignClient(
        value = "user-provider",  //绑定服务名
        fallback = UserClientFallback.class,  //配置熔断处理类
        configuration = FeignConfig.class //配置日志级别
)
//2、把生产者的请求url与方法原样配置过来
//@RequestMapping(value = "user")
public interface UserClient {  //这个类可以不注入就可以访问,是因为@FeignClient会注入到spring中
    /***
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/find/{id}")
    public User findById(@PathVariable(value = "id") Integer id);
}
