package com.wfr.learning.in.spring.spring.bean.factory;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/18
 */
public interface UserFactory {

    default User initUser() {
        return User.createUser();
    }
}
