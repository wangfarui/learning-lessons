package com.wfr.learning.ioc.container.overview.dependency.lookup;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);

        System.out.println("--- 实时查找 ---");
        System.out.println("根据Class类型查找" + user);
    }

    private static void lookupByName(BeanFactory beanFactory) {
        Object user2 = beanFactory.getBean("user2");

        System.out.println("--- 实时查找 ---");
        System.out.println("根据bean name查找" + (User) user2);
    }

}
