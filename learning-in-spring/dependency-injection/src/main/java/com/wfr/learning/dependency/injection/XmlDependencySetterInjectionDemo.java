package com.wfr.learning.dependency.injection;

import com.wfr.learning.ioc.container.overview.domain.IUser;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.ResolvableType;

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

        getBeanByBeanName(beanFactory);
        getBeanByBeanType(beanFactory);
    }

    /**
     * 通过 指定Bean名称 获取Spring Bean
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
     * 通过 指定Bean Class类型 获取Spring Bean
     * <br/>
     * DefaultListableBeanFactory#getBean 最终本质上还是调用了 AbstractBeanFactory#getBean(String, Class, Object) 方法,
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
