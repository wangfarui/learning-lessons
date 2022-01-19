package com.wfr.learning.spring.bean;

import com.wfr.learning.spring.bean.factory.DefaultUserFactory;
import com.wfr.learning.spring.bean.factory.SuperUserFactory;
import com.wfr.learning.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 单例Spring Bean注册示例
 *
 * @author wangfarui
 * @since 2022/1/19
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        UserFactory userFactory = new DefaultUserFactory();
        beanFactory.registerSingleton("userFactory", userFactory);
//        SuperUserFactory superUserFactory = new SuperUserFactory();
//        beanFactory.registerSingleton("superUserFactory", superUserFactory);

        applicationContext.register(SuperUserFactory.class);

        applicationContext.refresh();

        UserFactory factory = applicationContext.getBean("userFactory", UserFactory.class);
        System.out.println(factory.initUser());
        System.out.println("factory == userFactory: " + (factory == userFactory));

        Map<String, UserFactory> beansOfType = applicationContext.getBeansOfType(UserFactory.class);
        for (String key : beansOfType.keySet()) {
            UserFactory userFactory1 = beansOfType.get(key);
            System.out.println(key + "-" + userFactory1);
        }
        System.out.println(beansOfType);

        applicationContext.close();
    }
}
