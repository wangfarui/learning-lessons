package com.wfr.learning.in.spring.dependency.injection;

import com.wfr.learning.in.spring.dependency.injection.lazy.LazyUser;
import com.wfr.learning.in.spring.dependency.injection.lazy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * {@link Lazy}注解的依赖注入示例
 *
 * @author Wray
 * @since 2023/7/19
 */
@ComponentScan(basePackageClasses = LazyUser.class)
public class LazyAnnotationDependencyInjectionDemo {

    @Lazy
    @Autowired
    User user;

    @Autowired(required = false)
    LazyUser lazyUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        User user1 = applicationContext.getBean(User.class);
        LazyUser lazyUser1 = applicationContext.getBean(LazyUser.class);

        /**
         * demo.user: com.wfr.learning.in.spring.dependency.injection.lazy.User@6ab7a896
         * user1: com.wfr.learning.in.spring.dependency.injection.lazy.User@6ab7a896
         */
        System.out.println("demo.user: " + demo.user);
        System.out.println("user1: " + user1);

        System.out.println("demo.lazyUser: " + demo.lazyUser);
        System.out.println("lazyUser1: " + lazyUser1);

        /**
         * class com.wfr.learning.in.spring.dependency.injection.lazy.User$$EnhancerBySpringCGLIB$$b07f4fe2
         * class com.wfr.learning.in.spring.dependency.injection.lazy.User
         */
        System.out.println(demo.user.getClass());
        System.out.println(user1.getClass());
        
        System.out.println(demo.lazyUser.getClass());
        System.out.println(lazyUser1.getClass());

        System.out.println(demo.user == user1);
        System.out.println(demo.lazyUser == lazyUser1);

        applicationContext.close();
    }
}





