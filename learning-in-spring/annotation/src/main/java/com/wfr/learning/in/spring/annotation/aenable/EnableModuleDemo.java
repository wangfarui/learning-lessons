package com.wfr.learning.in.spring.annotation.aenable;

import com.wfr.learning.in.spring.annotation.domain.ITestComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Enable 模块驱动示例
 *
 * @author wangfarui
 * @since 2022/7/7
 */
@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnableModuleDemo.class);
        applicationContext.refresh();

        String helloWorld = applicationContext.getBean("helloWorld", String.class);
        System.out.println(helloWorld);

        String[] testComponentNames = applicationContext.getBeanNamesForType(ITestComponent.class);

        System.out.println(Arrays.toString(testComponentNames));

        applicationContext.close();
    }
}
