package com.wfr.learning.in.spring.ioc.container.overview.dependency.injection;

import com.wfr.learning.in.spring.ioc.container.overview.dependency.lookup.DependencyLookupDemo;
import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import com.wfr.learning.in.spring.ioc.container.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/13
 */
public class DependencyInjectionDemo extends DependencyLookupDemo {

    public static void main(String... args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源一: 自定义Bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

//        System.out.println(userRepository.getUsers());

        System.out.println(userRepository.getBeanFactory());

        System.out.println(userRepository.getBeanFactory() == beanFactory);
        System.out.println(userRepository.getBeanFactory().equals(beanFactory));

//        System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源二: 依赖注入 （容器内建依赖）
        ObjectFactory<User> objectFactory = userRepository.getObjectFactory();

        System.out.println(objectFactory.getObject());

        ObjectFactory<ApplicationContext> applicationContextObjectFactory = userRepository.getApplicationContextObjectFactory();

        System.out.println(applicationContextObjectFactory.getObject());

        // 依赖来源三: 容器内建Bean
        Environment environment = beanFactory.getBean(Environment.class);

        System.out.println(environment);


        compareBeanFactory(userRepository, applicationContext);
    }

    public static void compareBeanFactory(UserRepository userRepository, ApplicationContext applicationContext) {

        System.out.println("比较BeanFactory");

        System.out.println(userRepository.getBeanFactory());
        System.out.println(applicationContext.getAutowireCapableBeanFactory());

        System.out.println(userRepository.getBeanFactory() == applicationContext.getAutowireCapableBeanFactory());

    }

}
