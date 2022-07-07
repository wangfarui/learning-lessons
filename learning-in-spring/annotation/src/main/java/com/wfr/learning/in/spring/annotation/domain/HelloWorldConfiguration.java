package com.wfr.learning.in.spring.annotation.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * "HelloWorld" 配置类
 *
 * @author wangfarui
 * @since 2022/7/7
 */
@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() {
        return "Hello World";
    }
}
