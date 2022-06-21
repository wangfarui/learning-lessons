package com.wfr.learning.in.springboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * SpringBoot Test启动类
 *
 * @author wangfarui
 * @since 2022/6/21
 */
@SpringBootApplication
public class LearningInSpringBootTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LearningInSpringBootTestApplication.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String applicationName = environment.getProperty("spring.application.name");
        String id = environment.getProperty("wfr.id");
        System.out.println(applicationName + " - " + id);
    }
}
