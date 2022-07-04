package com.wfr.learning.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@link ApplicationEventMulticaster} 示例
 * <p>自定义taskExecutor、errorHandler
 *
 * @author wangfarui
 * @since 2022/7/4
 */
public class ApplicationEventMulticasterDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyApplicationListener.class);
        applicationContext.refresh();

        ApplicationEventMulticaster applicationEventMulticaster = applicationContext.getBean(
                AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class
        );

        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster eventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            ExecutorService executor = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("ApplicationEventMulticasterDemo"));
            eventMulticaster.setTaskExecutor(executor);
            eventMulticaster.setErrorHandler(e ->
                    System.err.println("Spring application event multicaster error, detailed reasons: " + e.getMessage())
            );

            applicationContext.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if (!executor.isShutdown()) {
                        executor.shutdown();
                    }
                }
            });
        }

        // 发送事件前添加监听器
        applicationContext.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                throw new RuntimeException("手动抛出预知异常");
            }
        });

        String[] beanNamesForType = applicationContext.getBeanNamesForType(ApplicationListener.class);
        System.out.println("ApplicationListener nums: " + beanNamesForType.length);
        System.out.println(Arrays.toString(beanNamesForType));

        applicationContext.publishEvent(new MySpringEvent("自定义事件消息"));

        applicationContext.close();
    }
}
