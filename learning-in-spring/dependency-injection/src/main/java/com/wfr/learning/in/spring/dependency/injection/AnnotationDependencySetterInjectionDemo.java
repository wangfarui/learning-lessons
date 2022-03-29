package com.wfr.learning.in.spring.dependency.injection;

import com.wfr.learning.in.spring.dependency.injection.bean.AnnotationInjectionBeans;
import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 {@link Bean} 依赖 Setter 方法注入示例
 *
 * @author wangfarui
 * @since 2022/2/24
 */
public class AnnotationDependencySetterInjectionDemo {

    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class, AnnotationInjectionBeans.class);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        applicationContext.close();
    }
}
