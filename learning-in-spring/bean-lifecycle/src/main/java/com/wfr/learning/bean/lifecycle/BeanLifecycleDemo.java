package com.wfr.learning.bean.lifecycle;

import com.wfr.learning.bean.lifecycle.bean.post.processor.MyDestructionAwareBeanPostProcessor;
import com.wfr.learning.bean.lifecycle.bean.post.processor.MyInstantiationAwareBeanPostProcessor;
import com.wfr.learning.ioc.container.overview.domain.SuperUser;
import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

import java.util.Arrays;
import java.util.Map;
import javax.annotation.PostConstruct;

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

    @Autowired
    private UserHolder userHolder;

    @Bean
    public User annotatedUser() {
        User user = new User();
        user.setId(9010L);
        user.setName("annotated user");
        return user;
    }

    @Bean
    @Lazy
    public User lazyUser() {
        User user = new User();
        user.setId(9020L);
        user.setName("lazy user");
        return user;
    }

    @Bean("userHolder")
    private UserHolder annotatedUserHolder(@Qualifier("annotatedUser") User user) {
        return new UserHolder(user);
    }

    /**
     * Bean生命周期总结为: 3、4、6、7、8、9、10、11、12、13、14、15、16、17
     */
    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 1. BeanDefinition 元信息配置
        // 2. BeanDefinition 元信息解析
        // 2.1 面向资源的BeanDefinition元信息解析
        loadBeanDefinitionByXml(beanFactory);
        loadBeanDefinitionByProperties(beanFactory);

        System.out.println();

        String[] beanDefinitionNames1 = beanFactory.getBeanDefinitionNames();
        System.out.printf("第一步加载后的BeanDefinition数量: %d 个, beanDefinitionNames: %s\n\n",
                beanDefinitionNames1.length, Arrays.toString(beanDefinitionNames1));

        // 2.2 面向注解的 BeanDefinition 元信息解析
        loadBeanDefinitionByAnnotation(beanFactory);

        System.out.println();

        String[] beanDefinitionNames2 = beanFactory.getBeanDefinitionNames();
        System.out.printf("第一步加载后的BeanDefinition数量: %d 个, beanDefinitionNames: %s\n\n",
                beanDefinitionNames2.length, Arrays.toString(beanDefinitionNames2));

        // 3. Spring Bean 注册阶段
        registerBeanDefinition(beanFactory);

        // 4. Spring BeanDefinition 合并阶段
        mergeBeanDefinition(beanFactory);

        // 5. Spring Bean Class 加载阶段
        loadClassLoader();

        // 6. Spring Bean 实例化前阶段
        // 7. Spring Bean 实例化阶段
        // 实例化阶段时, 会调用 MergedBeanDefinitionPostProcessor#postProcessMergedBeanDefinition 修改合并后的BeanDefinition,
        // 比如 CommonAnnotationBeanPostProcessor 继承的 InitDestroyAnnotationBeanPostProcessor 类会调用 findLifecycleMetadata 方法,
        // 查找 Bean Class 中的 @PostConstruct、@PreDestroy 标注的方法, 并存放于 InitDestroyAnnotationBeanPostProcessor.lifecycleMetadataCache 中
        // 8. Spring Bean 实例化后阶段
        // 9. Spring Bean 属性赋值前阶段
        // 10. Spring Bean 属性赋值阶段
        // 11. Spring Bean Aware接口回调阶段
        // Aware接口回调阶段 分为两个子阶段, 根据BeanFactory类型决定
        // BeanFactory有 BeanNameAware、BeanClassLoaderAware、BeanFactoryAware
        // ApplicationContext有 EnvironmentAware、EmbeddedValueResolverAware、ResourceLoaderAware、ApplicationEventPublisherAware、MessageSourceAware、ApplicationContextAware
        // 12. Spring Bean 初始化前阶段
        // 13. Spring Bean 初始化阶段
        // 初始化时回调 InitializingBean 接口的 afterPropertiesSet() 方法, 以及自定义的 initMethods
        // 14. Spring Bean 初始化后阶段
        doInstantiationAwareBeanPostProcessor(beanFactory);

        // 15. Spring Bean 初始化完成阶段
        afterSingletonsInstantiated();

        // 16. Spring Bean 销毁前阶段
        doDestructionAwareBeanPostProcessor(beanFactory);

        // 17. Spring Bean 销毁阶段
        destroyBeanPhase();

        // 18. Spring Bean 垃圾收集
        systemGCPhase();

        // finish-1: 手动触发BeanFactory后置处理器
        postProcessBeanFactory(beanFactory);

        // finish-2: 手动触发Bean后置处理器
        addBeanPostProcessor(beanFactory);

        // finish: 手动触发实例化非Lazy单例Bean
        preInstantiateSingletons(beanFactory);

        Map<String, User> beansOfType = beanFactory.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : beansOfType.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        BeanLifecycleDemo demo = beanFactory.getBean(BeanLifecycleDemo.class);
        System.out.println("demo.user: " + demo.user);
        System.out.println("demo.userHolder: " + demo.userHolder);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println("userHolder: " + userHolder);

        // 执行 Bean 销毁（容器内）
        beanFactory.destroyBean("userHolder", userHolder);
        // Bean 销毁并不意味着 Bean 垃圾回收了
        System.out.println(userHolder);

        // 销毁 BeanFactory 中所有的单例Bean
        beanFactory.destroySingletons();

        System.gc();

        Thread.sleep(5000L);

        System.gc();
    }

    /**
     * Spring Bean 垃圾收集阶段
     */
    private static void systemGCPhase() {

    }

    /**
     * 销毁阶段, 回调 @PreDestroy、DisposableBean 接口的 destroy() 方法、自定义 destroyMethods
     */
    private static void destroyBeanPhase() {

    }

    /**
     * 销毁前阶段, 回调 {@link DestructionAwareBeanPostProcessor#postProcessBeforeDestruction} 方法
     */
    private static void doDestructionAwareBeanPostProcessor(AbstractBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
    }

    /**
     * 初始化完成阶段是在所有Bean都初始化完成后, 再遍历进行. 并且仅对实现了 {@link SmartInitializingSingleton} 接口的Bean执行 afterSingletonsInstantiated 方法
     */
    private static void afterSingletonsInstantiated() {

    }

    /**
     * 实例化、属性赋值、初始化 阶段
     *
     * <p>在 {@link CommonAnnotationBeanPostProcessor#postProcessBeforeInitialization(Object, String)} 中会执行Bean{@link PostConstruct}注册的初始化方法
     */
    private static void doInstantiationAwareBeanPostProcessor(AbstractBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
    }

    /**
     * 加载 ClassLoader
     */
    private static void loadClassLoader() {

    }

    /**
     * 合并BeanDefinition, 最终所有Bean的BeanDefinition都将转换为 RootBeanDefinition
     *
     * @see AbstractBeanFactory#getMergedBeanDefinition(String, BeanDefinition, BeanDefinition)
     */
    private static void mergeBeanDefinition(DefaultListableBeanFactory beanFactory) {

    }

    /**
     * 基于 {@link BeanDefinitionReaderUtils#registerBeanDefinition} 注册BeanDefinition
     */
    private static void registerBeanDefinition(BeanDefinitionRegistry beanFactory) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SuperUser.class)
                .addPropertyValue("id", 9002L)
                .addPropertyValue("name", "super user")
                .addPropertyValue("address", "wuhan")
                .setInitMethodName("printUser");

        // 自动生成的Bean name格式为: com.wfr.learning.ioc.container.overview.domain.SuperUser#0
        // BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), beanFactory);

        String beanName = "customSuperUser";
        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinitionBuilder.getBeanDefinition(), beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, beanFactory);
    }

    /**
     * 在 beanFactory.preInstantiateSingletons() 之前, 需要注册相应的 BeanFactoryPostProcessor 和 BeanPostProcessor
     * <p>
     * 注册 BeanFactoryPostProcessor:
     * <br/>
     * 1. 是为了避免其中的 {@link SmartInitializingSingleton#afterSingletonsInstantiated} 执行时报 BeanFactory 空指针异常, 具体类为 {@link EventListenerMethodProcessor}
     * <br/>
     * 2. 是为了其中的 ConfigurationClassPostProcessor#postProcessBeanFactory 执行, 扫描由 AnnotatedBeanDefinitionReader.register
     * 注册的ConfiguraionClass, 并加载ConfigurationClass, 加载流程分析见 {@link ConfigurationClassDemo},
     * 其中就包括扫描当前ConfigurationClass下的@Bean注解, 注册其方法返回对象为BeanDefinition, 需要注意的是, 此时并没有invoke该方法
     * <br/>
     * 3. TODO 待分析其他特性
     * <p>
     * 以此才能在初始化bean name为 “beanLifecycleDemo” 的Bean时, 调用 BeanPostProcessor#postProcessProperties 方法,
     * 实现 AutowiredAnnotationBeanPostProcessor 的 @Autowired 注解注入
     */
    private static void preInstantiateSingletons(DefaultListableBeanFactory beanFactory) {
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 将 AnnotatedBeanDefinitionReader 注册的 BeanPostProcessor 都添加到 AbstractBeanFactory.beanPostProcessors 下
     */
    private static void addBeanPostProcessor(DefaultListableBeanFactory beanFactory) {
        Map<String, InstantiationAwareBeanPostProcessor> instantiationBeanPostProcessorMap = beanFactory.getBeansOfType(InstantiationAwareBeanPostProcessor.class);
        for (InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor : instantiationBeanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(instantiationAwareBeanPostProcessor);
        }
    }

    /**
     * 将 AnnotatedBeanDefinitionReader 注册的 BeanFactoryPostProcessor 都执行 postProcessBeanFactory 方法
     *
     * <p>ConfigurationClassPostProcessor#postProcessBeanFactory 会将当前ConfigurationClass里面的@Bean注册为BeanDefinition,
     * 以此决定了Bean的顺序, 在 DefaultListableBeanFactory#getBeansOfType 时返回的Map也是有序的LinkedHashMap。
     */
    private static void postProcessBeanFactory(DefaultListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessorMap.values()) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 基于 {@link AnnotatedBeanDefinitionReader} 解析元信息
     *
     * <p> AnnotatedBeanDefinitionReader 在实例化时Spring BeanFactory内部会注册一些默认的 BeanFactoryPostProcessor、 BeanPostProcessor、EventListenerFactory
     */
    private static void loadBeanDefinitionByAnnotation(BeanDefinitionRegistry beanFactory) {
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
        EncodedResource encodedResource = new EncodedResource(resource, "GBK");

        int beanDefinitionCountByProperties = propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.printf("PropertiesBeanDefinitionReader从[%s]中加载了 %d 个BeanDefinition\n",
                propertiesLocation, beanDefinitionCountByProperties);
    }
}
