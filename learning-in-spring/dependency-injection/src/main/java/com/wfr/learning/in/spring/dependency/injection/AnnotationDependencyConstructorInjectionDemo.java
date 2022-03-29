package com.wfr.learning.in.spring.dependency.injection;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 {@link Bean} 的依赖 Constructor 注入示例
 *
 * @author wangfarui
 * @since 2022/2/24
 */
public class AnnotationDependencyConstructorInjectionDemo {

//    @Bean
//    public User user() {
//        return User.createUser();
//    }

    private UserHolder userHolder;

    private static User user;

    /**
     * 构造方法注入时，若所有构造方法都未指定 @Autowired ,则默认调无参构造方法
     */
    @Autowired
    public AnnotationDependencyConstructorInjectionDemo() {
        User user = User.createUser();
        user.setName("通过无参构造方法创建");
        this.userHolder = new UserHolder(user);
    }

    /**
     * 入参的UserHolder不存在Spring Bean, applicationContext.refresh() 启动时会报错 {@link UnsatisfiedDependencyException}
     * <br/>
     * 基于构造方法初始化的Bean 会早于 {@link Bean} 初始化
     */
    public AnnotationDependencyConstructorInjectionDemo(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    public AnnotationDependencyConstructorInjectionDemo(User user) {
        AnnotationDependencyConstructorInjectionDemo.user = user;
    }

    @Bean
    public UserHolder userHolder(User user) {
        user.setName("当前类的userHolder");
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyConstructorInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "META-INF/dependency-lookup-context.xml";
        int beanDefinitions = reader.loadBeanDefinitions(location);
        System.out.printf("扫描到了%d个BeanDefinition%n", beanDefinitions);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        System.out.println("AnnotationDependencyConstructorInjectionDemo.user : " + AnnotationDependencyConstructorInjectionDemo.user);

        AnnotationDependencyConstructorInjectionDemo demo = applicationContext.getBean(AnnotationDependencyConstructorInjectionDemo.class);
        System.out.println("demo.userHolder : " + demo.userHolder);

        applicationContext.close();
    }


}
