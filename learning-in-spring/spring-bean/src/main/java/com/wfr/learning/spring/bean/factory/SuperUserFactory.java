package com.wfr.learning.spring.bean.factory;

import com.wfr.learning.ioc.container.overview.domain.SuperUser;
import com.wfr.learning.ioc.container.overview.domain.User;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/18
 */
public class SuperUserFactory implements UserFactory {

    @Override
    public SuperUser initUser() {
        SuperUser user = new SuperUser();
        user.setId(10L);
        user.setName("super-wangfarui");
        return user;
    }
}
