package com.wfr.learning.in.spring.configuration.metadata;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.BeanMetadataAttributeAccessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.AttributeAccessorSupport;
import org.springframework.beans.MutablePropertyValues;

/**
 * Spring Bean配置元信息示例
 *
 * <p>Spring Bean属性元信息: {@link MutablePropertyValues}、{@link PropertyValue}
 * <p>Spring Bean附加属性（不影响 Bean populate、initialize）: {@link AttributeAccessorSupport#attributes}、{@link BeanMetadataAttributeAccessor#source}
 *
 * @author wangfarui
 * @see MutablePropertyValues
 * @see BeanMetadataAttributeAccessor
 * @since 2022/3/26
 */
@SuppressWarnings("all")
public class ConfigurationMetadataDemo {

    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 10L);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinition.setAttribute("name", "WFR");
        beanDefinition.setSource(ConfigurationMetadataDemo.class);
        beanDefinition.setPrimary(true);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("user", beanDefinition);

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(User.class);
        beanFactory.registerBeanDefinition("rootUser", rootBeanDefinition);

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof User) {
                    BeanDefinition definition = beanFactory.getBeanDefinition(beanName);
                    if (ConfigurationMetadataDemo.class.equals(definition.getSource())) {
                        String name = (String) definition.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }
                }
                return bean;
            }
        });

        beanFactory.preInstantiateSingletons();

        User user = beanFactory.getBean(User.class);
        System.out.println(user);

    }
}
