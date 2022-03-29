package com.wfr.learning.in.spring.configuration.metadata;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import com.wfr.learning.in.spring.ioc.container.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * 基于 yaml 外部化配置元信息示例
 *
 * <p>{@link PropertySource}注册的属性元信息实例对象按照 "后进先出" 的逻辑进行排序. 即 yaml-property-source-3 > yaml-property-source-pro > yaml-property-source,
 * 相关底层实现为: {@link org.springframework.context.annotation.ConfigurationClassParser#processPropertySource}、{@link org.springframework.context.annotation.ConfigurationClassParser#addPropertySource}
 *
 * @author wangfarui
 * @see org.springframework.context.annotation.ConfigurationClassParser
 * @since 2022/3/28
 */
@PropertySources(
        {
                @PropertySource(
                        name = "yaml-property-source",
                        value = {"classpath:/META-INF/user-bean-definition.yaml"},
                        factory = YamlPropertySourceFactory.class
                ),
                @PropertySource(
                        name = "yaml-property-source-pro",
                        value = {"/META-INF/user-bean-definition-pro.yaml"},
                        factory = YamlPropertySourceFactory.class
                ),
                @PropertySource(
                        name = "yaml-property-source-3",
                        value = {"/META-INF/user-bean-definition-3.yaml"},
                        factory = YamlPropertySourceFactory.class
                ),
                @PropertySource(
                        name = "properties-property-source",
                        value = {"/META-INF/user-bean-definition.properties"}
                )
        }
)
@SuppressWarnings("all")
public class YamlPropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.city}") City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(YamlPropertySourceDemo.class);
        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        System.out.println(user);

        applicationContext.close();
    }
}
