package com.wfr.learning.in.spring.dependency.lookup;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 安全依赖查找
 *
 * @author wangfarui
 * @since 2022/2/14
 */
public class SafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SafetyDependencyLookupDemo.class);
        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);

        displayObjectFactoryGetObject(applicationContext);

        displayObjectProviderGetIfAvailable(applicationContext);

        displayListableBeanFactory(applicationContext);

        displayObjectProviderStream(applicationContext);

        applicationContext.close();
    }

    private static void displayObjectProviderStream(ApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStream", () -> beanProvider.stream().forEach(System.err::println));
    }

    private static void displayListableBeanFactory(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactory", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderGetIfAvailable(BeanFactory beanFactory) {
        ObjectProvider<User> beanProvider = beanFactory.getBeanProvider(User.class);
        printBeansException("displayObjectProviderGetIfAvailable", () -> beanProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(ApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () -> objectFactory.getObject());
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String methodName, Runnable runnable) {
        System.err.println("============================");
        System.err.println("运行方法: " + methodName);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public String getStr() {
        return "str";
    }
}
