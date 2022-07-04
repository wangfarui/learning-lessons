package com.wfr.learning.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 注入 {@link ApplicationEventPublisher} 示例
 *
 * @author wangfarui
 * @since 2022/7/4
 */
public class InjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware, ApplicationContextAware {

    /**
     * 其实注入的还是ApplicationContext, {@link ApplicationContext} 继承了 {@link ApplicationEventPublisher}
     */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationEventPublisher.publishEvent(new MySpringEvent("Spring event from @PostConstruct with applicationEventPublisher"));
        applicationContext.publishEvent(new MySpringEvent("Spring event from @PostConstruct with applicationContext"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(InjectingApplicationEventPublisherDemo.class);

        // 添加监听器
        applicationContext.addApplicationListener(new MyApplicationListener());

        applicationContext.refresh();

        applicationContext.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationEventPublisher.publishEvent(new MySpringEvent("Spring event from ApplicationContextAware"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new MySpringEvent("Spring event from ApplicationEventPublisher"));
    }
}
