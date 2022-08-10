package com.wfr.learning.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link PropertySources} 源属性示例
 *
 * @author wangfarui
 * @since 2022/8/10
 */
public class EnvironmentPropertySourceDemo {

    private static final String USER_NAME = "user.name";

    @Value("${user.name}")
    private String myName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnvironmentPropertySourceDemo.class);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String, Object> myPropertySourceMap = new HashMap<>();
        myPropertySourceMap.put(USER_NAME, "www");
        String propertySourceName = "my-property-source";
        MapPropertySource mapPropertySource = new MapPropertySource(propertySourceName, myPropertySourceMap);
        propertySources.addFirst(mapPropertySource);

        applicationContext.refresh();

        EnvironmentPropertySourceDemo demo = applicationContext.getBean(EnvironmentPropertySourceDemo.class);
        System.out.println(demo.myName);

        for (PropertySource<?> ps : propertySources) {
            System.out.println("propertySource source name: " + ps.getSource().getClass().getName());
            System.out.println("user.name: " + ps.getProperty(USER_NAME));
        }

        applicationContext.close();
    }
}
