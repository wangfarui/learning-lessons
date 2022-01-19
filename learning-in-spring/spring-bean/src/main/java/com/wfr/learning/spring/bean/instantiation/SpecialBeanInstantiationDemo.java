package com.wfr.learning.spring.bean.instantiation;

import com.wfr.learning.spring.bean.factory.DefaultUserFactory;
import com.wfr.learning.spring.bean.factory.SuperUserFactory;
import com.wfr.learning.spring.bean.factory.UserFactory;
import com.wfr.learning.spring.bean.factory.UserFactoryBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

/**
 * Spring Bean实例化特殊方式示例
 *
 * @author wangfarui
 * @since 2022/1/19
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) throws Exception {
        String path = "classpath:/META-INF/special-bean-instantiation-context.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();

        ServiceLoader<UserFactory> serviceLoader = autowireCapableBeanFactory.getBean("userFactoryByServiceLoader", ServiceLoader.class);

        displayServiceLoader(serviceLoader);

        UserFactoryBean bean = autowireCapableBeanFactory.createBean(UserFactoryBean.class);
        System.out.println(bean.getObject());

        UserFactory superUserFactory = autowireCapableBeanFactory.createBean(SuperUserFactory.class);
        System.out.println(superUserFactory.initUser());

        System.out.println(bean.getObject() == superUserFactory.initUser());

        System.out.println("--demo--");
        demoServiceLoader();
    }

    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        for (UserFactory userFactory : serviceLoader) {
            System.out.println(userFactory.initUser());
        }
    }

}
