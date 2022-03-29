package com.wfr.learning.in.spring.ioc.container.overview.container;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Arrays;
import java.util.Map;

/**
 * BeanFactory IOC容器示例
 *
 * @author wangfarui
 * @since 2022/1/14
 */
public class BeanFactoryAsIocContainerDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 配置文件 ClassPath路径
        String location = "classpath:/META-INF/dependency-injection-context.xml";
        // 加载配置
        int beanDefinitions = reader.loadBeanDefinitions(location);
        System.out.println("bean加载的数量: " + beanDefinitions);

        // 依赖查找
        lookupCollectionByType(beanFactory);


    }

    @SuppressWarnings("Duplicates")
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        System.out.println("--- 根据类型查找Bean集合 ---");
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;

            String[] beanDefinitionNames = listableBeanFactory.getBeanDefinitionNames();
            System.out.println("beanDefinitionNames: " + Arrays.toString(beanDefinitionNames));

            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("beansOfType: " + beansOfType);

        }
    }
}
