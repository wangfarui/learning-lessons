package com.wfr.learning.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 注解驱动异步事件处理器示例
 *
 * @author wangfarui
 * @since 2022/7/4
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedAsyncEventHandlerDemo.class);
        applicationContext.refresh();

        applicationContext.publishEvent(new MySpringEvent("Hello World"));

        applicationContext.close();
    }

    @Async
    @EventListener
    public void listenEvent(ApplicationEvent event) {
        System.out.printf("当前线程: [%s]监听到spring事件: %s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadExecutor(new CustomizableThreadFactory("annotated-async-event-handler-"));
    }
}
