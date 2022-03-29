package com.wfr.learning.in.spring.bean.lifecycle.bean.post.processor;

import com.wfr.learning.in.spring.bean.lifecycle.UserHolder;
import com.wfr.learning.in.spring.ioc.container.overview.domain.SuperUser;
import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import com.wfr.learning.in.spring.ioc.container.overview.enums.City;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
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

    /**
     * 实例化前阶段, 若返回非null, 则直接进入初始化后阶段(postProcessAfterInitialization)
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "lazyUser")) {
            return new User(1L, "BeforeInstantiation lazyUser");
        }
        return null;
    }

    /**
     * 实例化后阶段(即new BeanWrapper之后), 若返回false, 则跳过属性赋值阶段阶段(postProcessProperties), 直接进入初始化阶段(包括Aware接口回调阶段)
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "lazyUser") && bean instanceof User) {
            User user = (User) bean;
            user.setName("after instantiation user");
        }
        if (ObjectUtils.nullSafeEquals(beanName, "userHolder") && bean instanceof UserHolder) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.getUser().setName("after instantiation user holder");
        }
        if (ObjectUtils.nullSafeEquals(beanName, "customSuperUser") && bean instanceof SuperUser) {
            SuperUser superUser = (SuperUser) bean;
            superUser.setName("AfterInstantiation superUser");
            return true;
        }
        return true;
    }

    /**
     * 属性赋值前阶段
     *
     * <p>在此阶段，如果同时在 pvs 和 bean 对象上做了更改, 其优先级为 pvs > bean, 即pvs属性值会覆盖bean里面的值
     * <p>该阶段执行完之后, 进入属性赋值阶段
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "annotatedUser")) {
            if (pvs instanceof MutablePropertyValues) {
                MutablePropertyValues mpv = (MutablePropertyValues) pvs;
                mpv.addPropertyValue("city", City.SHANGHAI);
                mpv.addPropertyValue("name", "ProcessProperties user");
                mpv.add("id", 9011);
            }
            if (bean instanceof User) {
                User user = (User) bean;
                user.setWorkCities(new City[]{City.WUHAN, City.SHANGHAI});
                user.setCity(City.BEIJING);
            }
            // 在此时间段, bean最终会变成 city = SHANGHAI, name = ProcessProperties user, id = 9011
            // 之后, bean可能会被其他Bean做更改, 比如此示例中的 userHolder Bean, 将userHolder.user的name改为了after instantiation user holder
        }
        return pvs;
    }

    /**
     * 初始化前阶段, 若返回null, 则仍使用旧值
     *
     * <p>发生在Aware接口回调之后
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "annotatedUser")) {
            if (bean instanceof User) {
                User user = (User) bean;
                long id = user.getId() != null ? user.getId() : 0L;
                user.setId(id + 1);
            }
        }
        return bean;
    }

    /**
     * 初始化后阶段, 若返回null, 则仍使用旧值
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "lazyUser")) {
            return null;
        }
        if (ObjectUtils.nullSafeEquals(beanName, "annotatedUser")) {
            if (bean instanceof User) {
                User user = (User) bean;
                long id = user.getId() != null ? user.getId() : 0L;
                user.setId(id + 1);
            }
        }
        return bean;
    }
}
