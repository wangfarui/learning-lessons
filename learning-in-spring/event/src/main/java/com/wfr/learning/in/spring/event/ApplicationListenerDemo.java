package com.wfr.learning.in.spring.event;

import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 基于 {@link ApplicationListener} 示例
 *
 * @author wangfarui
 * @since 2022/7/1
 */
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ApplicationListenerDemo.class);

        applicationContext.addApplicationListener(event -> System.out.println("监听到事件: " + event));

        applicationContext.register(MyApplicationListener.class);

        applicationContext.refresh();
        applicationContext.start();
        applicationContext.stop();
        applicationContext.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello ApplicationEvent") {});
        applicationEventPublisher.publishEvent("Hello World");
//        applicationEventPublisher.publishEvent(new MyPayloadApplicationEvent(this, "Hello PayloadApplicationEvent"));
    }

    static class MyPayloadApplicationEvent extends PayloadApplicationEvent<String> {

        /**
         * Create a new PayloadApplicationEvent.
         *
         * @param source  the object on which the event initially occurred (never {@code null})
         * @param payload the payload object (never {@code null})
         */
        public MyPayloadApplicationEvent(Object source, String payload) {
            super(source, payload);
        }
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("MyApplicationListener监听到ContextRefreshedEvent事件: " + event);
        }
    }

    @EventListener
    @Order(2)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        println("@EventListener(onApplicationEvent) - 接收到 Spring ContextRefreshedEvent");
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent1(ContextRefreshedEvent event) {
        println("@EventListener(onApplicationEvent1) - 接收到 Spring ContextRefreshedEvent");
    }

    @EventListener
    @Async
    public void onApplicationEventAsync(ContextRefreshedEvent event) {
        println("@EventListener（异步） - 接收到 Spring ContextRefreshedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        println("@EventListener - 接收到 Spring ContextStartedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        println("@EventListener - 接收到 Spring ContextClosedEvent");
    }

    private static void println(Object printable) {
        System.out.printf("[线程：%s] : %s\n", Thread.currentThread().getName(), printable);
    }
}
