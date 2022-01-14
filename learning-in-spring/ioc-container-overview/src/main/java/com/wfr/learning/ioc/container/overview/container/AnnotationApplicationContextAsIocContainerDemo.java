package com.wfr.learning.ioc.container.overview.container;

import com.wfr.learning.ioc.container.overview.domain.User;
import com.wfr.learning.ioc.container.overview.domain.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/14
 */
public class AnnotationApplicationContextAsIocContainerDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(AnnotationApplicationContextAsIocContainerDemo.class);
        System.out.println(applicationContext2);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);
        applicationContext.register(UserFactory.class);
        applicationContext.refresh();

        System.out.println(applicationContext);

        // 依赖查找
        lookupCollectionByType(applicationContext);

        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory.getObject());

    }

//    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("wangfarui");
        return user;
    }

    @SuppressWarnings("Duplicates")
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        System.out.println("--- 根据类型查找Bean集合 ---");
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;

            String[] beanDefinitionNames = listableBeanFactory.getBeanDefinitionNames();
            System.out.println("beanDefinitionNames: " + Arrays.toString(beanDefinitionNames));

            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("beansOfType: " + beansOfType);

        }
    }
}
