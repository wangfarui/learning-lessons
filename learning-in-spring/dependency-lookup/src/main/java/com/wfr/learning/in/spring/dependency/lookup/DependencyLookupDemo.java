package com.wfr.learning.in.spring.dependency.lookup;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 *
 * <p>判断xml中的bean生成的Spring Bean的name值来源
 *
 * @author wangfarui
 * @since 2022/3/11
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String location = "classpath:/META-INF/dependency-lookup-property.xml";
        applicationContext.setConfigLocation(location);

        applicationContext.refresh();

        Map<String, User> beans = applicationContext.getBeansOfType(User.class);

        // 得出结论:
        // 1. <bean>标签的id属性和name属性都可以决定Spring Bean的name值
        // 2. id属性优先级 > name属性优先级
        // 3. id和name属性都不存在时, 以{当前Bean Class的全量类名 加 #自增数}为Spring Bean的name值, 自增数从0开始
        for (Map.Entry<String, User> entry : beans.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
