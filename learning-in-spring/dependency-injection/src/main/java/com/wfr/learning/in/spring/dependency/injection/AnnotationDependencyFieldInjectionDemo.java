package com.wfr.learning.in.spring.dependency.injection;

import com.wfr.learning.in.spring.dependency.injection.bean.AnnotationInjectionBeans;
import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 Java注解 的依赖 字段 注入示例
 *
 * @author wangfarui
 * @since 2022/2/26
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private UserHolder userHolder;

    @Autowired
    private UserHolder userHolder2;

    @Resource
    private UserHolder userHolder3;

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class, AnnotationInjectionBeans.class);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);
        System.out.println(demo.userHolder);

        System.out.println("userHolder == demo.userHolder : " + userHolder.equals(demo.userHolder));
        System.out.println("userHolder == demo.userHolder2 : " + userHolder.equals(demo.userHolder2));
        System.out.println("userHolder == demo.userHolder3 : " + userHolder.equals(demo.userHolder3));

        applicationContext.close();
    }
}
