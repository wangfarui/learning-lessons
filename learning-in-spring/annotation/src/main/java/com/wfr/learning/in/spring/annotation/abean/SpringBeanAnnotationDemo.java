package com.wfr.learning.in.spring.annotation.abean;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Spring {@link Bean} 注解的示例
 *
 * @author wangfarui
 * @since 2022/6/27
 */
@Configuration
public class SpringBeanAnnotationDemo {

    @Bean("configurationUser")
    public User configurationUser() {
        return User.createUser(1L);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringBeanAnnotationDemo.class);
        applicationContext.refresh();

        printUserByAnnotationSource(applicationContext);

        applicationContext.close();
    }

    /**
     * 打印来源于注解注册的 User Bean
     * <p>
     * 结果证明: 无论是Configuration, 还是@Component, 都可以使用@Bean注册.
     * 同理, 其他基于@Component派生的注解也可以使用@Bean注册Spring Bean
     * </p>
     */
    public static void printUserByAnnotationSource(ListableBeanFactory beanFactory) {
        String[] beanNamesForType = beanFactory.getBeanNamesForType(User.class);

        for (String beanName : beanNamesForType) {
            System.out.println(beanName);
        }
    }

    @Component
    static class ComponentAnnotationBean {

        @Bean("componentUser")
        public User componentUser() {
            return User.createUser(2L);
        }
    }

}
