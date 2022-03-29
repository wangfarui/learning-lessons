package com.wfr.learning.in.spring.dependency.injection.bean;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.annotation.Bean;

/**
 * 基于 {@link Bean} 注解注入的Bean
 *
 * @author wangfarui
 * @since 2022/2/24
 */
public class AnnotationInjectionBeans {

    @Bean
    public User userByAnnotation() {
        User user = User.createUser();
        user.setName("基于Bean注解注入的User");
        return user;
    }
}
