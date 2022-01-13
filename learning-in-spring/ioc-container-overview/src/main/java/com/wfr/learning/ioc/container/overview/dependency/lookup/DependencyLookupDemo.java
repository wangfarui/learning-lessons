package com.wfr.learning.ioc.container.overview.dependency.lookup;

import com.wfr.learning.ioc.container.overview.annotation.Super;
import com.wfr.learning.ioc.container.overview.domain.SuperUser;
import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/11
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        lookupByType(beanFactory);

        lookupByName(beanFactory);

        lookupByAnnotation(beanFactory);

        lookupByLazy(beanFactory);

        lookupCollectionByType(beanFactory);
    }

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

    private static void lookupByLazy(BeanFactory beanFactory) {
        @SuppressWarnings("unchecked")
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");

        User user = objectFactory.getObject();

        System.out.println("--- 延时查找 ---");
        System.out.println("根据ObjectFactory查找: " + user);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);

            System.out.println("--- 实时查找 ---");
            System.out.println("根据Annotation类型查找: " + beans);

        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);

        System.out.println("--- 实时查找 ---");
        System.out.println("根据Class类型查找: " + user);
    }

    private static void lookupByName(BeanFactory beanFactory) {
        Object user2 = beanFactory.getBean("user2");

        System.out.println("--- 实时查找 ---");
        System.out.println("根据bean name查找: " + (User) user2);
    }

}
