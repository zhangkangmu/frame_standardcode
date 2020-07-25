package com.itheima;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.itheima.exception.CustomerExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * 描述
 * @author ljh
 * @packagename com.itheima
 * @version 1.0
 * @date 2020/6/24
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelClient1Application {
    public static void main(String[] args) {
        SpringApplication.run(SentinelClient1Application.class, args);
    }

    @RestController
    public class TestController {

        //流控  QPS
        @GetMapping("/hello")
        public String hello() {
            return "hello sentinel";
        }

        //流控 线程数
        @GetMapping("/hello1")
        public String hello1() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello1 sentinel";
        }


        //流控模式  关联
        @GetMapping("/hello2")
        public String hello2() {
            return "hello2 sentinel";
        }

        //流控模式  关联
        @GetMapping("/hello3")
        public String hello3() {
            return "hello3 sentinel";
        }

        //流控效果  冷启动预热  预热 令牌桶
        @GetMapping("/hello4")
        public String hello4() {
            return "hello4 sentinel";
        }

        //流控效果  匀速排队处理   漏桶
        @GetMapping("/hello5")
        public String hello5() {
            return "hello5 sentinel";
        }

        //降级  RT 平均响应时间为基准进行降级处理
        @GetMapping("/hello6")
        public String hello6() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello6 sentinel";
        }

        //降级      异常比例
        @GetMapping("/hello7")
        public String hello7() {
            //模拟异常
            int i = 1 / 0;
            return "hello7 sentinel";
        }

        //降级      异常数量
        @GetMapping("/hello8")
        public String hello8() {
            //模拟异常
            int i = 1 / 0;
            return "hello8 sentinel";
        }

        //热点规则       参数限流
        @GetMapping("/hello9")
        @SentinelResource(value="hello9",blockHandler = "handlerExecption")//通过注解的方式来定义资源
        public String hello9(@RequestParam(required = false) String userId, @RequestParam(required = false)String skuId) {
            System.out.println(">>"+skuId);
            return "hello9 sentinel";
        }

        //需要自定一个一个兜底的方法 当流控规则发生的时候 触发该兜底方法  hystrix
        public String handlerExecption(String userId, String skuId, BlockException e){
            return "哈哈哈哈这个是当流控发送错误返回";
        }

        //====================================================sentinel注解的使用

        @GetMapping("/hello10")
        // fallback 用于指定兜底方法 作用是当业务方法报错的时候调用
        // blockHandler 用于指定兜底的方法 作用当流控发送的时候调用
        @SentinelResource(value="hello10",
                blockHandlerClass = CustomerExceptionHandler.class,blockHandler = "handleBlock",
                fallbackClass = CustomerExceptionHandler.class, fallback= "handleFallback")
        public String hello10() {
            int i=1/0;//业务发生错误 我也不希望返回500 应该给他返回一个兜底的数据
            return "hello10 sentinel";
        }

        public String hello10Fallback(){
            return "哈哈 上边业务调用报错除0了";
        }

        @GetMapping("/hello12")
        public String hello12(){
            int i=1/0;
            return "from 9001";
        }




    }
}
