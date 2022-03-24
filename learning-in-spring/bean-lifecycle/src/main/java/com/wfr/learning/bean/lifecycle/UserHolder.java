package com.wfr.learning.bean.lifecycle;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * {@link User} 的 Holder 类
 *
 * @author wangfarui
 * @since 2022/2/18
 */
public class UserHolder implements BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    private User user;

    private BeanFactory beanFactory;

    private String beanName;

    private ApplicationContext applicationContext;

    public UserHolder() {

    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    private void init() {
        System.out.println("Bean [" + beanName + "] 初始化中...");
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("Bean [" + beanName + "] 销毁中...");
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
