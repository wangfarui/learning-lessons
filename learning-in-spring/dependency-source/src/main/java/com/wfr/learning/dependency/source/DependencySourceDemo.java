package com.wfr.learning.dependency.source;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 依赖来源示例
 *
 * @author wangfarui
 * @since 2022/3/1
 */
public class DependencySourceDemo {

    // 注入在 postProcessProperties 方法执行，早于 setter注入，也早于 @PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 通过依赖注入方式, 获取Spring中内置的游离对象
     *
     * @see AbstractApplicationContext#prepareBeanFactory
     */
    @SuppressWarnings("all")
    @PostConstruct
    private void getResolvableDependencyObjectByDependencyInjection() {
        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory() " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
        System.out.println("ApplicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
    }

    /**
     * 通过依赖查找方式, 获取Spring中内置的游离对象
     */
    @PostConstruct
    private void getResolvableDependencyObjectByDependencyLookup() {
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
        getBean(ApplicationContext.class);
        User user = getBean(User.class);
        System.out.println(user);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (BeansException e) {
            System.err.println("当前类型" + beanType.getName() + " 无法在 BeanFactory 中查找!");
        }
        return null;
    }

    public static void main(String[] args) {
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions(location);

        applicationContext.refresh();


        // classPathXmlApplicationContext 与 applicationContext 实例对象是两个独立的Spring应用上下文，
        // 因此, 它们里面的Bean对象也是完全独立的
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(location);
        classPathXmlApplicationContext.refresh();
        Map<String, User> beansOfType = classPathXmlApplicationContext.getBeansOfType(User.class);
        System.out.println(beansOfType.size());

        applicationContext.close();

        classPathXmlApplicationContext.close();
    }
}
