package com.itheima.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.transaction.annotation.Transactional;

public class EmailEvent extends BaseEvent {

    private String email;

    private String context;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public EmailEvent(Object source) {
        super(source);
    }

    @Override
    public String getFlag() {
        return "email";
    }

    public EmailEvent(String email, String context) {
        this(context,email,context);
    }

    public EmailEvent(Object source, String email, String context) {
        super(source);
        this.email = email;
        this.context = context;

    }
}