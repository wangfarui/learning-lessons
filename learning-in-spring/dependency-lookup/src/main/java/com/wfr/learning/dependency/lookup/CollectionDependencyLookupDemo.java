package com.wfr.learning.dependency.lookup;

import com.wfr.learning.dependency.lookup.annotation.UserCollection;
import com.wfr.learning.ioc.container.overview.domain.SuperUser;
import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;

/**
 * 集合类型依赖查找示例
 * {@link org.springframework.beans.factory.ListableBeanFactory}
 *
 * @author wangfarui
 * @since 2022/2/14
 */
public class CollectionDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CollectionDependencyLookupDemo.class);
        applicationContext.refresh();

        Map<String, User> beans = applicationContext.getBeansOfType(User.class);
        for (String s : beans.keySet()) {
            User user = beans.get(s);
            System.out.println(user);
        }

        System.out.println("===========================");

        String[] beanNamesForType = applicationContext.getBeanNamesForType(User.class);
        System.out.println(Arrays.toString(beanNamesForType));

        System.out.println("============================");

        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(UserCollection.class);
        for (String s : beansWithAnnotation.keySet()) {
            User user = beans.get(s);
            System.out.println(user);
        }

        applicationContext.close();
    }

    @UserCollection
    @Bean
    public User getUser1() {
        return User.createUser();
    }

    @UserCollection
    @Bean
    public User getUser2() {
        User user = new User();
        user.setId(1002L);
        user.setName("wfr2");
        return user;
    }

    @UserCollection
    @Bean
    public SuperUser getSuperUser() {
        SuperUser superUser = new SuperUser();
        superUser.setId(1003L);
        superUser.setName("wfr3");
        superUser.setAddress("wuhan");
        return superUser;
    }

}
