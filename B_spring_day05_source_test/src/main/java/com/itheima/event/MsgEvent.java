package com.itheima.event;

import org.springframework.context.ApplicationEvent;

public class MsgEvent extends BaseEvent {

    private String phone;

    private String msg;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MsgEvent(Object source) {
        super(source);
    }

    @Override
    public String getFlag() {
        return "msg";
    }

    public MsgEvent(Object source, String phone, String msg) {
        super(source);
        this.phone = phone;
        this.msg = msg;
    }
}