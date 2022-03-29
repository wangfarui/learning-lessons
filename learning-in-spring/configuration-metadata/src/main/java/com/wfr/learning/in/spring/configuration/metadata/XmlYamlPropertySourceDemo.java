package com.wfr.learning.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;
import java.util.Properties;

/**
 * 基于 XML 资源的 YAML外部化配置示例
 *
 * @author wangfarui
 * @see org.springframework.beans.factory.config.YamlPropertiesFactoryBean
 * @see org.springframework.beans.factory.config.YamlMapFactoryBean
 * @since 2022/3/28
 */
public class XmlYamlPropertySourceDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "/META-INF/yaml-property-source-context.xml";
        reader.loadBeanDefinitions(location);

        Properties properties = beanFactory.getBean("yamlProperties", Properties.class);
        System.out.println(properties);

        Map<String, Object> map = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(map);
    }
}
