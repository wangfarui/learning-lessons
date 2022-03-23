package com.wfr.learning.bean.lifecycle;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

import java.util.Arrays;
import java.util.Map;

/**
 * Spring Bean生命周期示例
 *
 * @author wangfarui
 * @since 2022/3/1
 */
@SuppressWarnings("all")
public class BeanLifecycleDemo {

    @Autowired
    @Qualifier("annotatedUser")
    private User user;

    @Bean
    public User annotatedUser() {
        User user = new User();
        user.setId(9010L);
        user.setName("annotated user");
        return user;
    }

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 1. BeanDefinition 元信息配置; 面向资源的BeanDefinition元信息解析
        loadBeanDefinitionByXml(beanFactory);
        loadBeanDefinitionByProperties(beanFactory);

        System.out.println();

        String[] beanDefinitionNames1 = beanFactory.getBeanDefinitionNames();
        System.out.printf("第一步加载后的BeanDefinition数量: %d 个, beanDefinitionNames: %s\n\n",
                beanDefinitionNames1.length, Arrays.toString(beanDefinitionNames1));

        // 2. 面向注解的BeanDefinition元信息解析
        registerBeanDefinitionByAnnotation(beanFactory);

        System.out.println();

        String[] beanDefinitionNames2 = beanFactory.getBeanDefinitionNames();
        System.out.printf("第一步加载后的BeanDefinition数量: %d 个, beanDefinitionNames: %s\n\n",
                beanDefinitionNames2.length, Arrays.toString(beanDefinitionNames2));

        Map<String, User> beansOfType = beanFactory.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : beansOfType.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        // 将 AnnotatedBeanDefinitionReader 注册的 BeanFactoryPostProcessor 都执行 postProcessBeanFactory 方法
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessorMap.values()) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }

        // 将 AnnotatedBeanDefinitionReader 注册的 BeanPostProcessor 都添加到 AbstractBeanFactory.beanPostProcessors 下
        Map<String, InstantiationAwareBeanPostProcessor> instantiationBeanPostProcessorMap = beanFactory.getBeansOfType(InstantiationAwareBeanPostProcessor.class);
        for (InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor : instantiationBeanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(instantiationAwareBeanPostProcessor);
        }

        // 在 beanFactory.preInstantiateSingletons() 之前, 需要注册相应的 BeanFactoryPostProcessor 和 BeanPostProcessor
        // 注册 BeanFactoryPostProcessor 是为了避免 SmartInitializingSingleton#afterSingletonsInstantiated 执行时报 BeanFactory 空指针异常
        // 以此才能在初始化bean name为 “beanLifecycleDemo” 的Bean时, 调用 BeanPostProcessor#postProcessProperties 方法,
        // 实现 AutowiredAnnotationBeanPostProcessor 的 @Autowired 注解注入
        beanFactory.preInstantiateSingletons();

        BeanLifecycleDemo demo = beanFactory.getBean(BeanLifecycleDemo.class);
        System.out.println("demo.user: " + demo.user);

    }

    /**
     * 基于 {@link AnnotatedBeanDefinitionReader} 解析元信息
     *
     * <p> AnnotatedBeanDefinitionReader 在实例化时Spring BeanFactory内部会注册一些默认的 BeanFactoryPostProcessor、 BeanPostProcessor、EventListenerFactory
     */
    private static void registerBeanDefinitionByAnnotation(BeanDefinitionRegistry beanFactory) {
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionBeforeCount = beanFactory.getBeanDefinitionCount();
        reader.register(BeanLifecycleDemo.class);
        int beanDefinitionAfterCount = beanFactory.getBeanDefinitionCount();
        System.out.printf("AnnotatedBeanDefinitionReader加载了 %d 个BeanDefinition\n",
                beanDefinitionAfterCount - beanDefinitionBeforeCount);
    }

    /**
     * 基于 {@link XmlBeanDefinitionReader} 读取xml配置元信息
     */
    private static void loadBeanDefinitionByXml(BeanDefinitionRegistry beanFactory) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/bean-definition-configuration.xml";
        int beanDefinitionCount = reader.loadBeanDefinitions(location);
        System.out.printf("XmlBeanDefinitionReader从[%s]中加载了 %d 个BeanDefinition\n", location, beanDefinitionCount);
    }

    /**
     * 基于 {@link PropertiesBeanDefinitionReader} 读取properties配置元信息
     * <p>测试发现, 扫描非properties文件是无效的</p>
     *
     * <p>properties文件也可以同时定义多个BeanDefinition,根据xxx.yyy中小数点(.)前面的名称扫描bean name
     * 通过 {@link PropertiesBeanDefinitionReader#registerBeanDefinitions} 方法验证
     *
     * <p>针对每个扫描到的bean name, 生成BeanDefinition, 存放到 {@link BeanDefinitionRegistry} 下的子类中
     * 通过 {@link PropertiesBeanDefinitionReader#registerBeanDefinition} 方法验证
     */
    private static void loadBeanDefinitionByProperties(BeanDefinitionRegistry beanFactory) {
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String propertiesLocation = "META-INF/user2.properties";
        Resource resource = new ClassPathResource(propertiesLocation);

        int beanDefinitionCountByProperties = propertiesBeanDefinitionReader.loadBeanDefinitions(resource);
        System.out.printf("PropertiesBeanDefinitionReader从[%s]中加载了 %d 个BeanDefinition\n",
                propertiesLocation, beanDefinitionCountByProperties);
    }
}
