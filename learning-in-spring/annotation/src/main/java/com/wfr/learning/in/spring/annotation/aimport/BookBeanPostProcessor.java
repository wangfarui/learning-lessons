package com.wfr.learning.in.spring.annotation.aimport;

import com.wfr.learning.in.spring.annotation.domain.ThinkingInJavaBook;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/5/12
 */
public class BookBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, ThinkingInJavaBook.BEAN_NAME)) {
            System.out.println("postProcessProperties: " + ThinkingInJavaBook.BEAN_NAME);
            if (pvs instanceof MutablePropertyValues) {
                MutablePropertyValues propertyValues = (MutablePropertyValues) pvs;
                propertyValues.addPropertyValue("name", ThinkingInJavaBook.BOOK_NAME);
                propertyValues.addPropertyValue("author", "person");
            }
            pvs.forEach(t -> System.out.println(t.getName() + ": " + t.getValue()));
        }
        return null;
    }
}
