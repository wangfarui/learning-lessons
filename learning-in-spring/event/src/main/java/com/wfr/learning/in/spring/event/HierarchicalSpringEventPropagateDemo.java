package com.wfr.learning.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.core.ResolvableType;

/**
 * 层次性 Spring 事件传播示例
 *
 * @author wangfarui
 * @since 2022/7/4
 */
public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext parentApplicationContext = new AnnotationConfigApplicationContext();
        parentApplicationContext.setId("parent-context");
        parentApplicationContext.register(MySpringListener.class);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setParent(parentApplicationContext);
        applicationContext.setId("current-context");
        applicationContext.register(MySpringListener.class);

        parentApplicationContext.refresh();
        applicationContext.refresh();

        applicationContext.close();
        parentApplicationContext.close();
    }

    /**
     * 存在层级性的应用上下文时, Spring Event会 由外到内 的发送事件
     * @see org.springframework.context.support.AbstractApplicationContext#publishEvent(Object, ResolvableType)
     */
    @SuppressWarnings("all")
    public static class MySpringListener implements ApplicationListener<ApplicationContextEvent> {

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            System.out.printf("应用上下文 [%s], 监听到事件: %s\n", event.getApplicationContext().getId(), event.getSource());
        }
    }
}
