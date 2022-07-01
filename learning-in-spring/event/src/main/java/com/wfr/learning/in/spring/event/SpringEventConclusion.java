package com.wfr.learning.in.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * spring event 总结
 *
 * @author wangfarui
 * @since 2022/7/1
 */
public class SpringEventConclusion {

    /**
     * Spring 事件的核心组件
     * <ul>
     *     <li>事件对象: {@link ApplicationEvent}. 例如: {@link ApplicationContextEvent}</li>
     *     <li>事件监听器: {@link ApplicationListener}、注解实现为 {@link EventListener}</li>
     *     <li>事件发布器: {@link ApplicationEventPublisher} -> {@link ApplicationContext}</li>
     *     <li>事件广播器: {@link ApplicationEventMulticaster} -> {@link SimpleApplicationEventMulticaster}</li>
     * </ul>
     */
    public static void coreComponent() {
    }
}
