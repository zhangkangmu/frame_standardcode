package com.itheima.exception;

/**
 * @ClassName SysException
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/20 15:34
 * @Version V1.0
 */
public class SysException extends Exception {

    private String message;

    // 赋值异常的信息
    public SysException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
