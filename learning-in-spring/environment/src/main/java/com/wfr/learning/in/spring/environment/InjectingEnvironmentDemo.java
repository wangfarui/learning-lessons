package com.wfr.learning.in.spring.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * {@link Environment} 依赖注入示例
 *
 * @author wangfarui
 * @since 2022/8/9
 */
public class InjectingEnvironmentDemo implements EnvironmentAware {

    @Autowired
    private Environment environment;

    private Environment environment2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingEnvironmentDemo.class);

        applicationContext.refresh();

        InjectingEnvironmentDemo demo = applicationContext.getBean(InjectingEnvironmentDemo.class);

        // 实例对象为 StandardEnvironment
        System.out.println(demo.environment2 == demo.environment);

        applicationContext.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment2 = environment;
    }
}
