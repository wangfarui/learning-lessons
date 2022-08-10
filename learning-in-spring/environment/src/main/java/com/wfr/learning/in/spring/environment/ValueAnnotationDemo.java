package com.wfr.learning.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link Value} 注解示例
 *
 * @author wangfarui
 * @since 2022/8/9
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String myUserName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ValueAnnotationDemo.class);

        applicationContext.refresh();

        ValueAnnotationDemo demo = applicationContext.getBean(ValueAnnotationDemo.class);

        System.out.println(demo.myUserName);

        applicationContext.close();
    }
}
