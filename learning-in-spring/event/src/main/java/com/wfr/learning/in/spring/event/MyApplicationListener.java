package com.wfr.learning.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 自定义 Spring {@link ApplicationListener} 监听器
 *
 * @author wangfarui
 * @since 2022/7/4
 */
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.printf("A.....当前线程: [%s]监听到spring事件: %s\n", Thread.currentThread().getName(), event);
    }
}
