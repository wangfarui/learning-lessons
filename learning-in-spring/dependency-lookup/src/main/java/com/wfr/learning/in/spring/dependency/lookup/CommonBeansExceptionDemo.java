package com.wfr.learning.in.spring.dependency.lookup;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 常见的Bean异常
 *
 * @author wangfarui
 * @since 2022/2/14
 */
public class CommonBeansExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CommonBeansExceptionDemo.class);

        // display BeanInstantiationException
        // 注册 BeanDefinition Bean Class 是一个 CharSequence 接口
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
//        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // display BeanCreationException
        BeanDefinitionBuilder beanDefinitionBuilder1 = BeanDefinitionBuilder.genericBeanDefinition(InnerTest.class);
        applicationContext.registerBeanDefinition("InnerTest", beanDefinitionBuilder1.getBeanDefinition());

        applicationContext.refresh();

//        displayNoSuchBeanDefinitionException(applicationContext);

//        displayNoUniqueBeanDefinitionException(applicationContext);


        applicationContext.close();
    }

    private static void displayNoUniqueBeanDefinitionException(ApplicationContext applicationContext) {
        String bean = applicationContext.getBean(String.class);
    }

    private static void displayNoSuchBeanDefinitionException(ApplicationContext applicationContext) {
        applicationContext.getBean(User.class);
    }


    @Bean
    public String getName1() {
        return "name1";
    }

    @Bean
    public String getName2() {
        return "name2";
    }

    class InnerTest implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("InnerTest#afterPropertiesSet");
        }
    }
}
