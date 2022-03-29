package com.wfr.learning.in.spring.configuration.metadata;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import com.wfr.learning.in.spring.ioc.container.overview.enums.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于 properties 外部化配置元信息示例
 *
 * @author wangfarui
 * @see Environment
 * @see MutablePropertySources
 * @see PropertiesPropertySource
 * @since 2022/3/28
 */
@PropertySource(value = {"classpath:/META-INF/user-bean-definition.properties"}, encoding = "GBK")
public class PropertiesPropertySourceDemo {

    @Autowired
    private Environment environment;

    /**
     * user.name 为 Java Properties 获取系统属性的变量值, 优先级高于properties文件
     */
    @Bean
    public static User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${customUser.city}") City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        Map<String, Object> propertySourceMap = new HashMap<>(4);
        propertySourceMap.put("customUser.city", City.SHANGHAI);
//        propertySourceMap.put("user.name", "wang");
        MapPropertySource mapPropertySource = new MapPropertySource("custom-property-source", propertySourceMap);

        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        // 移除系统Properties、系统Environment
        propertySources.remove(ConfigurableApplicationContext.SYSTEM_PROPERTIES_BEAN_NAME);
        propertySources.remove(ConfigurableApplicationContext.SYSTEM_ENVIRONMENT_BEAN_NAME);
        // 新增自定义PropertiesSource到第一个
        applicationContext.getEnvironment().getPropertySources().addFirst(mapPropertySource);

        applicationContext.register(PropertiesPropertySourceDemo.class);

        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        System.out.println(user);

        PropertiesPropertySourceDemo demo = applicationContext.getBean(PropertiesPropertySourceDemo.class);
        System.out.println(demo.environment);

        applicationContext.close();
    }
}
