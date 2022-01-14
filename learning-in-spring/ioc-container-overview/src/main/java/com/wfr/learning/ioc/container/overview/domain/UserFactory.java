package com.wfr.learning.ioc.container.overview.domain;

import org.springframework.beans.factory.FactoryBean;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/14
 */
public class UserFactory implements FactoryBean<User> {


    @Override
    public User getObject() {
        User user = new User();
        user.setId(1L);
        user.setName("wangfarui2");
        return user;
    }

    @Override
    public Class<User> getObjectType() {
        return User.class;
    }
}
