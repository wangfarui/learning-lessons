package com.wfr.learning.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring 事件
 *
 * @author wangfarui
 * @since 2022/7/4
 */
public class MySpringEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
