package com.wfr.learning.in.spring.application.context.lifecycle;

/**
 * Spring 应用上下文生命周期示例
 *
 * @author wangfarui
 * @since 2022/3/21
 */
public class ApplicationContextLifecycleDemo {

    public static void main(String[] args) {

    }

    /**
     * AbstractApplicationContext#postProcessBeanFactory(ConfigurableListableBeanFactory) 方法，交由子类实现，例如 AbstractRefreshableApplicationContext 的实现中会添加 ServletContext 相关内容
     *
     * AbstractApplicationContext#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory) 方法，主要是执行 BeanFactoryPostProcessor 和 BeanDefinitionRegistryPostProcessor 的处理，会做以下事情：
     *
     * 1. 如果当前 Spring 应用上下文是 BeanDefinitionRegistry 类型，则执行当前 Spring 应用上下文中所有 BeanDefinitionRegistryPostProcessor、BeanFactoryPostProcessor 的处理，以及底层 BeanFactory 容器中 BeanDefinitionRegistryPostProcessor 的处理，处理顺序如下：
     *    1. 当前 Spring 应用上下文中所有 BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry
     *    2. 底层 BeanFactory 容器中所有 BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry（优先级：PriorityOrdered > Ordered > 无）
     *    3. 当前 Spring 应用上下文和底层 BeanFactory 容器中所有 BeanDefinitionRegistryPostProcessor#postProcessBeanFactory
     *    4. 当前 Spring 应用上下文中所有 BeanFactoryPostProcessor#postProcessBeanFactory
     * 2. 否则，执行当前 Spring 应用上下文中所有 BeanFactoryPostProcessor#postProcessBeanFactory
     * 3. 执行底层 BeanFactory 容器中所有 BeanFactoryPostProcessor#postProcessBeanFactory，上面已经处理过的会跳过，执行顺序和上面一样：PriorityOrdered > Ordered > 无
     */
    public void refresh() {

    }
}
