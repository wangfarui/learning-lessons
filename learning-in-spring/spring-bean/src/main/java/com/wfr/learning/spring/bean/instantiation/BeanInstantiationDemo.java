package com.wfr.learning.spring.bean.instantiation;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/18
 */
public class BeanInstantiationDemo {

    private final User user;

    public BeanInstantiationDemo(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        // 配合 XML 配置文件
        String path = "classpath:/META-INF/bean-instantiation-context.xml";
        // 创建 BeanFactory 容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);

        // 1. 构造器实例化Bean
        BeanInstantiationDemo user1 = beanFactory.getBean("user-by-constructor-method", BeanInstantiationDemo.class);
        System.out.println(user1.user);

        // 2. 静态方法实例化Bean
        User user2 = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println(user2);

        // 3. Bean工厂实例化Bean
        User user3 = beanFactory.getBean("user-by-bean-factory", User.class);
        System.out.println(user3);

        // 3. FactoryBean实例化Bean
        User user4 = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(user4);


    }
}
