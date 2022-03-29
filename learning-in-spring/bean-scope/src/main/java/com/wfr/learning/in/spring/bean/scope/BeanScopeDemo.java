package com.wfr.learning.in.spring.bean.scope;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.SimpleInstantiationStrategy;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Spring Bean作用域示例
 *
 * @author wangfarui
 * @since 2022/3/1
 */
@SuppressWarnings("all")
public class BeanScopeDemo {

    /**
     * {@link Bean} 的大致处理流程:
     * 1. 被 {@link AbstractApplicationContext#refresh()} -> {@link AbstractApplicationContext#invokeBeanFactoryPostProcessors} 调用
     * 2. 在 {@link org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsForBeanMethod} 中被注册为 BeanDefinition
     * 3. 在 {@link AbstractApplicationContext#finishBeanFactoryInitialization} 中被初始化
     */
    @Bean
    @Primary
    public User singletonUser() {
        User user = User.createUser(20L);
        user.setName("singleton user");
        return user;
    }

    @Bean(name = "singletonUser2")
    public User singletonUser2() {
        User user = User.createUser(21L);
        user.setName("singleton user 2");
        return user;
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototypeUser() {
        User user = new User();
        user.setId(System.nanoTime());
        user.setName("prototype user");
        return user;
    }

    /**
     * {@link Autowired} 的大致处理流程:
     * 1. 被 {@link AbstractApplicationContext#refresh()} -> {@link AbstractApplicationContext#registerBeanPostProcessors} 调用
     * 2. 在 {@link AbstractAutowireCapableBeanFactory#instantiateBean} -> {@link SimpleInstantiationStrategy#instantiate} 中实例化当前 {@link Bean} 所在类(即当前所在类必定也是一个Spring Bean)
     * 3. 在第二步后面进过一系列处理后, 进入 {@link AutowiredAnnotationBeanPostProcessor#AutowiredAnnotationBeanPostProcessor()} 构造方法, 将多个 注入注解 存放在 {@link BeanWrapperImpl#wrappedObject} 中
     * 4. TODO 调试发现，singletonUser 的注入还是发生在 {@link AbstractApplicationContext#finishBeanFactoryInitialization} 阶段
     * 5. 最后会在 {@link SimpleInstantiationStrategy#instantiate(RootBeanDefinition, String, BeanFactory, Object, Method, Object...)} 方法中, 通过反射给 BeanScopeDemo 实例对象的 singletonUser 字段注入 User Bean对象
     */
    @Autowired
    private User primaryUser;

    @Autowired
    @Qualifier("singletonUser2")
    private User singletonUser2;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> userMap;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);
        applicationContext.refresh();

        // 结论一：
        // Singleton Bean 无论依赖查找还是依赖注入，均为同一个对象
        // Prototype Bean 无论依赖查找还是依赖注入，均为新生成的对象

        // 结论二：
        // 如果依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 均会存在一个
        // Prototype Bean 有别于其他地方的依赖注入 Prototype Bean

        // 结论三：
        // 无论是 Singleton 还是 Prototype Bean 均会执行初始化方法回调
        // 不过仅 Singleton Bean 会执行销毁方法回调

        scopedBeansByLookup(applicationContext);

        System.out.println("==========================");

        scopedBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("demo.primaryUser: " + demo.primaryUser);
        System.out.println("demo.singletonUser2: " + demo.singletonUser2);
        System.out.println("demo.prototypeUser1: " + demo.prototypeUser1);
        System.out.println("demo.prototypeUser2: " + demo.prototypeUser2);
        System.out.println("demo.userMap: " + demo.userMap);
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        User primaryUser = applicationContext.getBean(User.class);
        System.out.println("primaryUser: " + primaryUser);

        User singletonUser2 = applicationContext.getBean("singletonUser2", User.class);
        System.out.println("singletonUser2: " + singletonUser2);
        System.out.println("singletonUser2.hashCode(): " + singletonUser2.hashCode());
        User singletonUser22 = applicationContext.getBean("singletonUser2", User.class);
        System.out.println("singletonUser22.hashCode(): " + singletonUser22.hashCode());

        User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
        User prototypeUser2 = applicationContext.getBean("prototypeUser", User.class);
        User prototypeUser3 = applicationContext.getBean("prototypeUser", User.class);
        System.out.println("prototypeUser: " + prototypeUser);
        System.out.println("prototypeUser2: " + prototypeUser2);
        System.out.println("prototypeUser3: " + prototypeUser3);

        // 依赖查找 Object.class 类型的Bean时, 会查找到所有Bean
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);
        for (Map.Entry<String, Object> entry : beansOfType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getClass());
        }
    }
}
