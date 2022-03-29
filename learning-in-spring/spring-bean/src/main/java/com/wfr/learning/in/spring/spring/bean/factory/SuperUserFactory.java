package com.wfr.learning.in.spring.spring.bean.factory;

import com.wfr.learning.in.spring.ioc.container.overview.domain.SuperUser;

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
        user.setAddress("wuhan");
        return user;
    }
}
