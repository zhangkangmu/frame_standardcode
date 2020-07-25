package com.itheima.event;

import org.springframework.context.ApplicationEvent;

public class StationEvent extends BaseEvent {

    private Long userId;

    private String msg;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getFlag() {
        return "station";
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public StationEvent(Object source) {
        super(source);
    }


    public StationEvent(Object source, Long userId, String msg) {
        super(source);
        this.userId = userId;
        this.msg = msg;
    }
}