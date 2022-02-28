package com.wfr.learning.dependency.injection;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

/**
 * 集合类型的依赖注入示例
 *
 * @author wangfarui
 * @since 2022/2/28
 */
public class CollectionTypeDependencyInjectionDemo {

    /**
     * Collection<User> 初始化的实例对象是 {@link java.util.LinkedHashMap}
     */
    @Autowired
    private Collection<User> userList;

    public static void main(String[] args) {
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CollectionTypeDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions(location);

        applicationContext.refresh();

        CollectionTypeDependencyInjectionDemo demo = applicationContext.getBean(CollectionTypeDependencyInjectionDemo.class);

        System.out.println(demo.userList);

        applicationContext.close();
    }
}
