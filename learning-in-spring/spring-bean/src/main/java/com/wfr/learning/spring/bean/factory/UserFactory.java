package com.wfr.learning.spring.bean.factory;

import com.wfr.learning.ioc.container.overview.domain.User;

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
