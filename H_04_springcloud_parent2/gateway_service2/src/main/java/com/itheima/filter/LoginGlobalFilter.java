package com.itheima.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 实现登录拦截全局过滤功能
 * @author zhangyuhong
 * @version 1.0
 * @description com.itheima.filter
 * @date 2020-4-25
 */
@Component   //访问如果不行的话要看yml里是否配置了多一个前缀
public class LoginGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 实现过滤器的逻辑
     * @param exchange  交换机-获取请求输入，请求头...请求参数与response对象
     * @param chain 用于设置放行
     * @return 放行，不放行-设置响应的错误状态码
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //检查用户有没有在请求中，传入token参数
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        //" ".isEmpty()  false
        //" ".isBlank()  true
        //没有传入token，返回400
        if (StringUtils.isBlank(token)) {
            //返回400错误代码
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            //返回response
            return exchange.getResponse().setComplete();
        }else {//有传入参数，就放行
            //放行
            return chain.filter(exchange);
        }
    }

    //设置过滤器的执行顺序，返回一个int,数据越小，越先执行
    @Override
    public int getOrder() {
        return 0;
    }
}
