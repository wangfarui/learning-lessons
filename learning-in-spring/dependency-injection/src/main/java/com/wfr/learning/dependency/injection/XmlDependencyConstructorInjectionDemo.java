package com.wfr.learning.dependency.injection;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 资源的依赖 Constructor 注入示例
 *
 * @author wangfarui
 * @since 2022/2/18
 */
public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:META-INF/dependency-constructor-injection.xml";
        reader.loadBeanDefinitions(xmlResourcePath);

        User user = beanFactory.getBean(User.class);

        System.out.println(user);
    }
}
