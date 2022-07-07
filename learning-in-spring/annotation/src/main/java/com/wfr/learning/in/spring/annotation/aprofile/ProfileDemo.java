package com.wfr.learning.in.spring.annotation.aprofile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * {@link Profile} 示例
 *
 * @author wangfarui
 * @since 2022/7/7
 */
public class ProfileDemo {

    private static final String NUMBER_BEAN_NAME = "number";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ProfileDemo.class);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setDefaultProfiles("one");

        // 同时存在多个profile时, 排除default profile后, 以第一个注册的Bean为主. 而不是受active profile的先后顺序影响
        environment.setActiveProfiles( "two", "three");

        applicationContext.refresh();

        Integer number = applicationContext.getBean(NUMBER_BEAN_NAME, Integer.class);
        System.out.println(number);

        applicationContext.close();
    }

    @Bean(NUMBER_BEAN_NAME)
    @Profile("one")
    public Integer one() {
        return 1;
    }

    @Bean(NUMBER_BEAN_NAME)
//    @Profile("three")
    @Conditional(ThreeProfileCondition.class)
    public Integer three() {
        return 3;
    }

    @Bean(NUMBER_BEAN_NAME)
    @Profile("two")
    public Integer two() {
        return 2;
    }

}
