package com.wfr.learning.dependency.injection;

import com.wfr.learning.dependency.injection.annotation.InjectUser;
import com.wfr.learning.dependency.injection.annotation.MyAutowired;
import com.wfr.learning.dependency.injection.annotation.MyInjectUser;
import com.wfr.learning.dependency.injection.annotation.MySuperInjectUser;
import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;

import java.util.Optional;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 自定义注解的依赖注入示例
 *
 * @author wangfarui
 * @since 2022/3/1
 */
@SuppressWarnings("all")
public class CustomAnnotationDependencyInjectionDemo {

    @Autowired          // 依赖查找（处理） + 延迟
    @Lazy
    private User lazyUser;

    // DependencyDescriptor ->
    // 必须（required=true）
    // 实时注入（eager=true)
    // 通过类型（User.class）
    // 字段名称（"user"）
    // 是否首要（primary = true)
    @Autowired
    private User user1;

    @Autowired
    private Optional<User> userOptional;

    @MyAutowired
    private User user2;

    @InjectUser
    private User user3;

    @Inject
    private User user4;

    @MyInjectUser
    private User user5;

    @MySuperInjectUser
    private User user6;


    /**
     * 指定Bean名称为 {@link AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME} 时，会覆盖默认的BeanPostProcessor
     *
     * @see AutowiredAnnotationBeanPostProcessor#buildAutowiringMetadata  -> 474 line
     * @see AnnotationConfigUtils#registerAnnotationConfigProcessors -> 169 line
     */
//    @Bean(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Bean("myCustomInjectAnnotations")
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setAutowiredAnnotationType(InjectUser.class);
        return processor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CustomAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        applicationContext.refresh();

        CustomAnnotationDependencyInjectionDemo demo = applicationContext.getBean(CustomAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.lazyUser : " + demo.lazyUser);
        System.out.println("demo.userOptional : " + demo.userOptional.get());
        System.out.println("demo.user1 : " + demo.user1);
        System.out.println("demo.user2 : " + demo.user2);
        System.out.println("demo.user3 : " + demo.user3);
        System.out.println("demo.user4 : " + demo.user4);
        System.out.println("demo.user5 : " + demo.user5);
        System.out.println("demo.user6 : " + demo.user6);

        applicationContext.close();
    }
}
