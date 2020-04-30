package com.hong.proxy.jdkProxy;

/**
 * @ClassName Producer
 * @Description TODO
 * @Author ly
 * @Company
 * @Date 2020/3/16 10:53
 * @Version V1.0
 */
public interface Producer {
    // 销售
    public void saleProduct(float money);
    // 售后
    public void afterService(float money);
}
