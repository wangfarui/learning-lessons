package com.wfr.learning.in.spring.environment;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * {@link PropertySourcesPlaceholderConfigurer} 处理属性占位符示例
 *
 * @author wangfarui
 * @since 2022/8/9
 */
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/placeholders-resolver.xml");

        User user = applicationContext.getBean("user", User.class);

        System.out.println(user);

        applicationContext.close();
    }
}
