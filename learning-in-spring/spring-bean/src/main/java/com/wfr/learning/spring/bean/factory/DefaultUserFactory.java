package com.wfr.learning.spring.bean.factory;

import com.wfr.learning.ioc.container.overview.domain.User;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/18
 */
public class DefaultUserFactory implements UserFactory {

    @Override
    public User initUser() {
        return User.createUser();
    }

}
