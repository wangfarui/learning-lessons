package com.wfr.learning.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link DefaultListableBeanFactory#resolvableDependencies} 非Spring容器管理对象, 也叫游离对象
 *
 * @author wangfarui
 * @since 2022/3/10
 */
@SuppressWarnings("all")
public class ResolvableDependencyDemo {

    @Autowired
    private String strVal;

    @Autowired
    private Integer intVal;

    @PostConstruct
    private void init() {
        System.out.println("strVal: " + strVal);
        System.out.println("intVal: " + intVal);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencyDemo.class);

        DefaultListableBeanFactory defaultListableBeanFactory = applicationContext.getDefaultListableBeanFactory();
        defaultListableBeanFactory.registerResolvableDependency(String.class, "hello ");
        defaultListableBeanFactory.registerResolvableDependency(Integer.class, 1001);
        // 覆盖同 dependencyType 的对象
        defaultListableBeanFactory.registerResolvableDependency(Integer.class, 1002);

        /**
         * 在applicationContext.refresh()时调用, 具体流程如下:
         * {@link AbstractApplicationContext#invokeBeanFactoryPostProcessors}
         * {@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors}
         * PostProcessorRegistrationDelegate 是内部类
         *
         * 覆盖同 dependencyType 的对象
         */
        applicationContext.addBeanFactoryPostProcessor(t -> {
            t.registerResolvableDependency(String.class, "hello wfr");
        });

        applicationContext.refresh();

        applicationContext.close();
    }
}
