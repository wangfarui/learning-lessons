package com.wfr.learning.in.spring.dependency.injection;

import com.wfr.learning.in.spring.dependency.injection.bean.AnnotationInjectionBeans;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 基于 接口回调(xxxAware) 依赖注入示例
 * 例如: BeanFactoryAware, ApplicationContextAware, EnvironmentAware, BeanNameAware
 *
 * @author wangfarui
 * @since 2022/2/28
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, BeanNameAware, EnvironmentAware {

    private BeanFactory beanFactory;

    private String beanName;

    private Environment environment;

    private static BeanFactory staticBeanFactory;

    private static String staticBeanName;

    private static Environment staticEnvironment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBean("testAwareInterfaceDemo", AwareInterfaceDependencyInjectionDemo.class);
        applicationContext.register(AnnotationInjectionBeans.class, AwareInterfaceDependencyInjectionDemo.class);
        applicationContext.refresh();

        AwareInterfaceDependencyInjectionDemo demo = applicationContext.getBean("testAwareInterfaceDemo", AwareInterfaceDependencyInjectionDemo.class);

        System.out.println(demo.beanFactory);
        System.out.println(demo.beanName);
        System.out.println(demo.environment);

        System.out.println("=======================================");

        System.out.println(AwareInterfaceDependencyInjectionDemo.staticBeanFactory);
        System.out.println(AwareInterfaceDependencyInjectionDemo.staticBeanName);
        System.out.println(AwareInterfaceDependencyInjectionDemo.staticEnvironment);

        System.out.println("=======================================");

        System.out.println("demo.beanFactory == staticBeanFactory : " + demo.beanFactory.equals(staticBeanFactory));
        System.out.println("demo.environment == staticEnvironment : " + demo.environment.equals(staticEnvironment));

        applicationContext.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        AwareInterfaceDependencyInjectionDemo.staticBeanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        AwareInterfaceDependencyInjectionDemo.staticBeanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        AwareInterfaceDependencyInjectionDemo.staticEnvironment = environment;
    }
}
