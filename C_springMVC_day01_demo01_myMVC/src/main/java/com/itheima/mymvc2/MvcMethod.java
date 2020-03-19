package com.itheima.mymvc2;

import java.lang.reflect.Method;

/**
 * @author liuyp
 * @date 2020/03/02
 */
public class MvcMethod {
    private Method method;
    private Object obj;

    public MvcMethod(Method method, Object obj) {
        this.method = method;
        this.obj = obj;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
