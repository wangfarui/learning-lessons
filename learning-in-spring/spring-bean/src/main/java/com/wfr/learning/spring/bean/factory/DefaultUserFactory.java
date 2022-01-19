package com.wfr.learning.spring.bean.factory;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/18
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @Override
    public User initUser() {
        return User.createUser();
    }

    @PostConstruct
    public void initPostConstruct() {
        System.out.println("@PostConstruct 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet 初始化中...");
    }

    public void initMethod() {
        System.out.println("@Bean#initMethod 初始化中...");
    }

    @PreDestroy
    public void destroyPostConstruct() {
        System.out.println("@PreDestroy 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy 销毁中...");
    }

    public void destroyMethod() {
        System.out.println("@Bean#destroyMethod 销毁中...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前对象正在被JVM垃圾回收...");
        super.finalize();
    }
}
