package com.wfr.learning.in.spring.dependency.injection;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;

/**
 * {@link User} 的 Holder 类
 *
 * @author wangfarui
 * @since 2022/2/18
 */
public class UserHolder {

    private User user;

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

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
