package com.hong.controller;

import com.hong.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangyuhong
 * @version 1.0
 * @description com.hong.controller
 * @date 2020-4-23
 */
@RestController
@RequestMapping("consumer")
//开启全局熔断
@DefaultProperties(defaultFallback = "defaultFailBack")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;//RestTemplate是Rest的HTTP客户端模板工具类

    @Autowired
    private DiscoveryClient discoveryClient; //DiscoveryClient用于从eureka中获取服务实例列表


    /****
     * 服务降级处理方法
     * @return
     */
    public User failBack(Integer id){
        User user = new User();
        user.setUsername("服务降级,默认处理！");
        return  user;
    }

    /****
     * 全局的服务降级处理方法
     * @return
     */
    public User defaultFailBack(){
        User user = new User();
        user.setUsername("Default-服务降级,全局默认处理！");
        return  user;
    }



    @RequestMapping("find/{id}")
    //配置熔断器-局部
    //@HystrixCommand(defaultFallback = "failBack")
    //全局熔断器
    @HystrixCommand
    public User findById(@PathVariable Integer id) {
        //String url = "http://localhost:18081/user/find/" + id;

       /* List<ServiceInstance> instances = discoveryClient.getInstances("user-provider");
        //获取第一个实例
        ServiceInstance instance = instances.get(0);

        //第一种方式拼接uri
        //localhost18081
        System.out.println(instance.getUri());
        //拼接url
        //String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/find/" + id;
        String url = instance.getUri() + "/user/find/" + id;*/

        //第二种方式拼接uri
       //负载均衡的url--使用应用程序名称定位
        String url = "http://user-provider/user/find/" + id;

        //发起http请求，从provider中获取用户数据
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
}
