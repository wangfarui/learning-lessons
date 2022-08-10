package com.wfr.learning.in.spring.environment;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.Environment;

/**
 * {@link Environment} 依赖查找示例
 *
 * @author wangfarui
 * @since 2022/8/9
 */
public class LookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LookupEnvironmentDemo.class);

        applicationContext.refresh();

        LookupEnvironmentDemo demo = applicationContext.getBean(LookupEnvironmentDemo.class);
        Environment environment = applicationContext.getBean(AbstractApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);

        System.out.println(demo.environment == environment);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
