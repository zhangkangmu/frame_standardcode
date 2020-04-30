package com.hong.proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName Client
 * @Description TODO
 * @Author ly
 * @Company
 * @Date 2020/3/16 10:55
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        // 没有代理
        final Producer2 producer = new Producer2();
//        producer.saleProduct(10000f);
//        producer.afterService(10000f);
        // 【需求】：使用代理，对销售的方法saleProduct进行代理，代理提供服务收取10000元的0.2 = 2000 ，8000元给厂商
        // CGLIB代理（对接口进行代理、对类进行代理）
        // 使用目标对象创建代理对象
        /**
         * Enhancer.create
         *   Class class：代理对象和目标对象要有相同的字节码
             callBack h：回调函数，当访问目标对象方法的时候，先会执行该函数中的intercept方法

           * 方法的返回值，就是代理对象，CGLIB代理Producer2$$EnhancerByCGLIB$$8f8f2322
         *
         * *************************************************************************
         *   invoke方法的参数：
         *     Object proxy：代理对象
         *     Method method：目标对象中的方法对象，该对象可以执行invoke()方法，用来调用目标对象中的方法
         *     Object[] args：代理对象和目标对象中对应的方法参数
         *     MethodProxy methodProxy：代理对象的中的方法对象（用的很少）
         *  invoke方法的返回值：表示目标对象方法的返回值
         */
        Producer2 proxyProducer = (Producer2)Enhancer.create(producer.getClass(),
                new MethodInterceptor() {
                    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                        System.out.println("执行代理商...");
                        // 方法的参数
                        Float money = (Float)args[0];
                        // 目标对象的方法名称，如果是saleProduct销售的方法，需要改变销售模式，收取手续费2000
                        Object returnValue = null;
                        if("saleProduct".equals(method.getName())){
                            // 通过代理对象执行目标对象
                            returnValue = method.invoke(producer, money*0.8f);
                        }else{
                            returnValue = method.invoke(producer, money);
                        }
                        return returnValue;
                    }
                });

//        Producer proxyProducer = (Producer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
//                producer.getClass().getInterfaces(),
//                new InvocationHandler() {
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("执行代理商...");
//                        // 方法的参数
//                        Float money = (Float)args[0];
//                        // 目标对象的方法名称，如果是saleProduct销售的方法，需要改变销售模式，收取手续费2000
//                        Object returnValue = null;
//                        if("saleProduct".equals(method.getName())){
//                            // 通过代理对象执行目标对象
//                            returnValue = method.invoke(producer, money*0.8f);
//                        }else{
//                            returnValue = method.invoke(producer, money);
//                        }
//                        return returnValue;
//                    }
//                });
        // 通过代理对象方法
        proxyProducer.saleProduct(10000f);
        proxyProducer.afterService(10000f);
    }
}
