package com.wfr.learning.bean.lifecycle.bean.post.processor;

import com.wfr.learning.bean.lifecycle.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * 基于 {@link InstantiationAwareBeanPostProcessor} 对 Bean 的实例化阶段和初始化阶段做自定义后置处理
 *
 * <p>针对Bean name为 'userHolder' 的Bean做后置处理
 *
 * @author wangfarui
 * @since 2022/3/24
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    private String dealBeanName = "userHolder";

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        if (containUserHolder(beanName)) {
//            return new UserHolder();
//        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private boolean containUserHolder(String beanName) {
        return ObjectUtils.nullSafeEquals(dealBeanName, beanName);
    }
}
