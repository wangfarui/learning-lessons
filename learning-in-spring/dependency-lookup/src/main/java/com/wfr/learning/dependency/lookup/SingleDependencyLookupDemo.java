package com.wfr.learning.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 单一类型依赖查找示例
 * {@link org.springframework.beans.factory.BeanFactory}
 *
 * @author wangfarui
 * @since 2022/2/14
 */
public class SingleDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SingleDependencyLookupDemo.class);
        applicationContext.refresh();

        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);

        String s = beanProvider.getIfAvailable();

        System.out.println(s);

    }

    @Bean
    public String getMyName() {
        return "WFR";
    }
}
