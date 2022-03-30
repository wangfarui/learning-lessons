package com.wfr.learning.in.spring.i18n;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;

/**
 * Spring Boot 场景下自定义 {@link MessageSource} Bean
 *
 * @author wangfarui
 * @see MessageSource
 * @see MessageSourceAutoConfiguration
 * @see ReloadableResourceBundleMessageSource
 * @since 2022/3/29
 */
@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo {

    /**
     * 在 Spring Boot 场景中，Primary Configuration Sources(Classes) 高于 *AutoConfiguration
     */
    @Bean(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class)
                .web(WebApplicationType.NONE)
                .run(args);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        String messageSourceBeanName = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME;

        if (beanFactory.containsBean(messageSourceBeanName)) {
            System.out.println(beanFactory.getBean(messageSourceBeanName));
            if (beanFactory.containsBeanDefinition(messageSourceBeanName)) {
                System.out.println(beanFactory.getBeanDefinition(messageSourceBeanName));
            } else {
                System.out.println("none bean definition");
            }
        } else {
            System.out.println("none bean");
        }

    }
}
