package com.wfr.learning.spring.bean.definition;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * {@link AnnotationConfigApplicationContext} Annotation bean definition demo
 *
 * @author wangfarui
 * @since 2022/1/17
 */
//@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class 配置类，加载其中的Bean
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

//        registerUserBeanDefinition(applicationContext, "user2");

        registerUserBeanDefinition(applicationContext, null);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        Map<String, User> beans = applicationContext.getBeansOfType(User.class);

        System.out.println(beans);

        applicationContext.close();
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 2L)
                .addPropertyValue("name", "wangfarui2");

        if (StringUtils.hasText(beanName)) {
            // 指定Bean命名 注册 BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名 Bean 注册方法
            // 生成通用名称的Bean，通用名称默认调 BeanDefinitionReaderUtils#uniqueBeanName
            // bean name默认为 java全包路径 + 类名 + “#” + 自增数(从0开始)
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }

    }

//    @Configuration
    public static class Config {

//        @Bean
        public User user() {
            User user = new User();
            user.setName("wangfarui");
            user.setId(1L);
            return user;
        }
    }

}
