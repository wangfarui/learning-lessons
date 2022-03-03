package com.wfr.learning.dependency.injection;

import com.wfr.learning.ioc.container.overview.domain.IUser;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.ResolvableType;
import org.springframework.beans.factory.ObjectFactory;

/**
 * 基于 XML 资源的依赖 Setter 方法注入示例
 *
 * @author wangfarui
 * @since 2022/2/15
 */
public class XmlDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String resourcePath = "classpath:/META-INF/dependency-setter-injection.xml";
        reader.loadBeanDefinitions(resourcePath);

        // 获取单例Bean的示例
        getBeanByBeanName(beanFactory);
        getBeanByBeanType(beanFactory);

        // 获取原型(多例)Bean的示例
        getPrototypeBeanByBeanName(beanFactory);
    }

    /**
     * 通过 指定Bean名称 获取Spring Prototype Bean
     * <br/>
     * 每次获取多例对象时, 都会调用 {@link AbstractAutowireCapableBeanFactory#doCreateBean} 方法生成新的实例对象,
     * 而每次获取单例对象时, {@link AbstractBeanFactory#doGetBean -> 249 line} 会先获取缓存是否已存在该对象, 存在则直接返回
     *
     * @see AbstractBeanFactory#doGetBean -> 336 line
     * @see AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])
     */
    @SuppressWarnings("all")
    private static void getPrototypeBeanByBeanName(BeanFactory beanFactory) {
        System.out.println("=============================");
        UserHolder prototypeUserHolder1 = beanFactory.getBean("prototypeUserHolder", UserHolder.class);
        System.out.println("prototypeUserHolder1 = " + prototypeUserHolder1);
        System.out.println("prototypeUserHolder1.getUser().hashCode() = " + prototypeUserHolder1.getUser().hashCode());
        System.out.println("prototypeUserHolder1.hashCode() = " + prototypeUserHolder1.hashCode());


        UserHolder prototypeUserHolder2 = beanFactory.getBean("prototypeUserHolder", UserHolder.class);
        System.out.println("prototypeUserHolder2 = " + prototypeUserHolder2);
        System.out.println("prototypeUserHolder2.getUser().hashCode() = " + prototypeUserHolder2.getUser().hashCode());
        System.out.println("prototypeUserHolder2.hashCode() = " + prototypeUserHolder2.hashCode());
    }

    /**
     * 通过 指定Bean名称 获取Spring Single Bean
     * <br/>
     * 获取单例Bean时, 调用的是 {@link AbstractBeanFactory#doGetBean -> 320 line}, 这里使用了lambda表达式,
     * 其实先调用了 {@link AbstractAutowireCapableBeanFactory#doCreateBean} 方法创建了一个 {@link ObjectFactory} 对象,
     * 再然后执行 {@link DefaultSingletonBeanRegistry#getSingleton} 相关方法
     *
     * @see AbstractBeanFactory#getBean(String)
     * @see DefaultSingletonBeanRegistry#getSingleton(String)
     * @see AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])
     */
    @SuppressWarnings("all")
    private static void getBeanByBeanName(BeanFactory beanFactory) {
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    /**
     * 通过 指定Bean Class类型 获取Spring Single Bean
     * <br/>
     * DefaultListableBeanFactory#getBean 最终本质上还是调用了 AbstractBeanFactory#getBean(String, Class, Object) 方法,
     * (代码示例: DefaultListableBeanFactory#resolveNamedBean 调 AbstractBeanFactory#getBean)
     * 而 AbstractBeanFactory#getBean -> doGetBean() 最终本质上还是调用了 DefaultSingletonBeanRegistry#getSingleton 方法,
     * DefaultSingletonBeanRegistry#getSingleton 则是去拿当前类下的Map变量,
     * 即 singletonObjects、singletonFactories、earlySingletonObjects
     *
     * @see DefaultListableBeanFactory#getBean(Class)
     * @see DefaultListableBeanFactory#resolveBean(ResolvableType, Object[], boolean)
     * @see DefaultListableBeanFactory#resolveNamedBean(ResolvableType, Object[], boolean)
     */
    @SuppressWarnings("all")
    private static void getBeanByBeanType(BeanFactory beanFactory) {
        // 基于 接口类 获取实例类Bean
        IUser superUser = beanFactory.getBean(IUser.class);
        System.out.println(superUser);
    }
}
