package com.wfr.learning.bean.lifecycle.bean.post.processor;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * 基于 {@link DestructionAwareBeanPostProcessor} 对 Bean 的销毁阶段做自定义后置处理
 *
 * @author wangfarui
 * @since 2022/3/25
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "annotatedUser")) {
            if (bean instanceof User) {
                User user = (User) bean;
                long id = user.getId() != null ? user.getId() : 0L;
                user.setId(id + 1);
                System.out.println("postProcessBeforeDestruction - user : " + user);
            }
        }
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return true;
    }
}
