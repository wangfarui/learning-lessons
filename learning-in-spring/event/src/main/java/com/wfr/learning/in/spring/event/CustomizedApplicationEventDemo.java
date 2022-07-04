package com.wfr.learning.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义 Spring {@link ApplicationEvent} 事件
 *
 * @author wangfarui
 * @since 2022/7/4
 */
public class CustomizedApplicationEventDemo {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.addApplicationListener(new MyApplicationListener());
        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.printf("B.....当前线程 [%s], 监听到事件: %s\n", Thread.currentThread().getName(), event);
            }
        });

        // ContextRefreshedEvent 事件
        applicationContext.refresh();

        // PayloadApplicationEvent 事件
        applicationContext.publishEvent("Hello World");
        // 自定义事件
        applicationContext.publishEvent(new MySpringEvent("H1"));
        applicationContext.publishEvent(new MySpringEvent2("H2"));

        // ContextClosedEvent 事件
        applicationContext.close();
    }
}
