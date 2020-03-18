package com.itheima.proxy.cglibProxy;

/**
 * @ClassName Producer2
 * @Description TODO
 * @Author ly
 * @Company
 * @Date 2020/3/16 10:54
 * @Version V1.0
 */
// 目标对象
public class Producer2{
    /**
     * 销售
     * @param money
     */
    public void saleProduct(float money){
        System.out.println("销售产品，并拿到钱："+money);
    }

    /**
     * 售后
     * @param money
     */
    public void afterService(float money){
        System.out.println("提供售后服务，并拿到钱："+money);
    }
}
