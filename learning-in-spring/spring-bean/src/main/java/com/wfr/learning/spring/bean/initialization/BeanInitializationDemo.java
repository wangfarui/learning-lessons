package com.wfr.learning.spring.bean.initialization;

import com.wfr.learning.spring.bean.factory.DefaultUserFactory;
import com.wfr.learning.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.Map;

/**
 * Spring Bean 初始化 Demo
 *
 * @author wangfarui
 * @since 2022/1/19
 */
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);

        System.out.println("开始启动Spring应用上下文...");
        applicationContext.refresh();
        System.out.println("Spring应用上下文启动完成...");

        Map<String, UserFactory> beansOfType = applicationContext.getBeansOfType(UserFactory.class);

        System.out.println(beansOfType);

        applicationContext.close();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    @Lazy(value = false)
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
