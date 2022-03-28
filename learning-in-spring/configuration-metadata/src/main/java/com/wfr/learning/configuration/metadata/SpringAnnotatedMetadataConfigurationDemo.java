package com.wfr.learning.configuration.metadata;

import com.wfr.learning.ioc.container.overview.domain.User;
import com.wfr.learning.ioc.container.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于 Spring 注解 获取 Spring IoC 容器元信息示例
 *
 * @author wangfarui
 * @since 2022/3/28
 */
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource(value = "classpath:/META-INF/user-bean-definition.yaml", factory = YamlPropertySourceFactory.class)
public class SpringAnnotatedMetadataConfigurationDemo {

    @Bean(name = "annotatedUser")
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.city}") City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        context.register(SpringAnnotatedMetadataConfigurationDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();
        // beanName 和 bean 映射
        Map<String, User> usersMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : usersMap.entrySet()) {
            System.out.printf("User Bean name : %s , content : %s \n", entry.getKey(), entry.getValue());
        }
        // 关闭 Spring 应用上下文
        context.close();
    }
}
