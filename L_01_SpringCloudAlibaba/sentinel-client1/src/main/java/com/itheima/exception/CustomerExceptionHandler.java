package com.itheima.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/***
 * 描述
 * @author ljh
 * @packagename com.itheima.exception
 * @version 1.0
 * @date 2020/6/24
 */
public class CustomerExceptionHandler {
    //方法一定是static 的方法 资源的方法参数类型和个数一定要和该处理的方法保持一致
    public static String handleBlock(BlockException e) {
        return "如果发生了【sentinel流控异常的是】错误 就返回的兜底的数据";
    }
    //方法一定是static 的方法
    public static String handleFallback(Throwable e) {
        return "如果发生了【系统自带的异常的】错误 就返回的兜底的数据";
    }

}
