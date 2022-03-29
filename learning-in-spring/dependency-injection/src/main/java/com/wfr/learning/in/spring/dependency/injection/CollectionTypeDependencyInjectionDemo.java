package com.wfr.learning.in.spring.dependency.injection;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;
import java.util.stream.Stream;

/**
 * 集合类型的依赖注入示例
 *
 * @see org.springframework.beans.TypeConverterDelegate#convertIfNecessary
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveMultipleBeans
 *
 * @author wangfarui
 * @since 2022/2/28
 */
@SuppressWarnings("all")
public class CollectionTypeDependencyInjectionDemo {

    /**
     * Collection<User> 初始化的实例对象是 {@link java.util.LinkedHashMap}
     */
    @Autowired
    private Collection<User> userCollection;

    /**
     * List<User> 初始化的实例对象是 {@link java.util.ArrayList}
     */
    @Autowired
    private List<User> userList;

    /**
     * Set<User> 初始化的实例对象是 {@link java.util.LinkedHashSet}
     */
    @Autowired
    private Set<User> userSet;

    @Autowired
    private User[] userArray;

    /**
     * 并非 StreamDependencyDescriptor
     */
    // @Autowired
    private Stream<User> userStream;

    public static void main(String[] args) {
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CollectionTypeDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions(location);

        applicationContext.refresh();

        CollectionTypeDependencyInjectionDemo demo = applicationContext.getBean(CollectionTypeDependencyInjectionDemo.class);

        System.out.println(demo.userCollection);
        System.out.println(demo.userList);
        System.out.println(demo.userSet);
        System.out.println(Arrays.toString(demo.userArray));
        if (demo.userStream != null) {
            demo.userStream.forEach(System.out::println);
        } else {
            System.out.println("demo.userStream is null");
        }

        applicationContext.close();
    }
}
