package com.wfr.learning.dependency.lookup;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 延迟依赖查找示例
 * {@link org.springframework.beans.factory.ObjectFactory}
 * {@link org.springframework.beans.factory.ObjectProvider}
 *
 * @author wangfarui
 * @since 2022/2/14
 */
public class DelayDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DelayDependencyLookupDemo.class);
        applicationContext.refresh();

        // 不能这样强转
//        ObjectFactory<User> objectFactory = (ObjectFactory<User>) applicationContext.getBeanFactory();
//        User user = objectFactory.getObject();
//        System.out.println(user);
        // ObjectProvider is ObjectFactory
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        User user = objectFactory.getObject();
        System.out.println(user);

        // ObjectProvider 参考 SingleDependencyLookupDemo

        applicationContext.close();
    }

    @Bean
    public User getDefaultUser() {
        return User.createUser();
    }
}
