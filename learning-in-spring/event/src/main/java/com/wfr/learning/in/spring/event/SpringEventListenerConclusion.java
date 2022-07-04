package com.wfr.learning.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * 关于 Spring {@link ApplicationListener} 的总结
 *
 * @author wangfarui
 * @since 2022/7/4
 */
public class SpringEventListenerConclusion {

    /**
     * 在 ApplicationContext 刷新前, 注册的（实现了ApplicationListener）类会被添加为 ApplicationListener, 并作为一个Bean.
     * <p>
     * 在 ApplicationContext 刷新后, 注册的类只会被作为一个Bean.
     * <p>
     * 在 ApplicationContext 刷新前后, 调用 AbstractApplicationContext#addApplicationListener 方法, 都会被添加为 ApplicationContext, 但不会被注册为 Bean.
     * <p>
     * 同时, 注册的 ApplicationListener 只会对后面的事件生效.
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyApplicationListener.class);
        applicationContext.refresh();
        applicationContext.register(MyApplicationListener2.class);

        applicationContext.addApplicationListener(new MyApplicationListener3());

        String[] beanNamesForType = applicationContext.getBeanNamesForType(ApplicationListener.class);
        System.out.println("ApplicationListener nums: " + beanNamesForType.length);
        System.out.println(Arrays.toString(beanNamesForType));

        applicationContext.close();
    }


    public static class MyApplicationListener2 implements ApplicationListener<ApplicationEvent> {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            System.out.println("[MyApplicationListener2] 监听到事件: " + event);
        }
    }

    public static class MyApplicationListener3 implements ApplicationListener<ApplicationEvent> {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            System.out.println("[MyApplicationListener3] 监听到事件: " + event);
        }
    }
}
