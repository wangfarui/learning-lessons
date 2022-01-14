package com.wfr.learning.ioc.container.overview.dependency.injection;

import com.wfr.learning.ioc.container.overview.dependency.lookup.DependencyLookupDemo;
import com.wfr.learning.ioc.container.overview.domain.User;
import com.wfr.learning.ioc.container.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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

        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);

//        System.out.println(userRepository.getUsers());

        System.out.println(userRepository.getBeanFactory());

        System.out.println(userRepository.getBeanFactory() == beanFactory);
        System.out.println(userRepository.getBeanFactory().equals(beanFactory));

//        System.out.println(beanFactory.getBean(BeanFactory.class));

        ObjectFactory<User> objectFactory = userRepository.getObjectFactory();

        System.out.println(objectFactory.getObject());

        ObjectFactory<ApplicationContext> applicationContextObjectFactory = userRepository.getApplicationContextObjectFactory();

        System.out.println(applicationContextObjectFactory.getObject());

    }

}
