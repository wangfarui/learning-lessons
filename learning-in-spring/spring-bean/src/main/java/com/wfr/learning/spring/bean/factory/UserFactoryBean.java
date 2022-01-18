package com.wfr.learning.spring.bean.factory;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/18
 */
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
