package com.wfr.learning.in.spring.annotation.acomponent;

import com.wfr.learning.in.spring.annotation.domain.ITestComponent;
import com.wfr.learning.in.spring.annotation.domain.TestComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 基于 {@link Component}、{@link ComponentScan} 的示例
 *
 * @author wangfarui
 * @since 2022/7/7
 */
@MyComponentScan2(scanBasePackageClasses = TestComponent.class)
//@MyComponentScan2(scanBasePackages = "com.wfr.learning.in.spring.annotation.domain")
public class ComponentScanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ComponentScanDemo.class);
        applicationContext.refresh();

        String[] testComponentNames = applicationContext.getBeanNamesForType(ITestComponent.class);

        System.out.println(Arrays.toString(testComponentNames));

        applicationContext.close();
    }
}
