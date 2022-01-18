package com.wfr.learning.ioc.container.overview.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/1/11
 */
@Getter
@Setter
@ToString
public class User {

    private Long id;

    private String name;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("wangfarui");
        return user;
    }
}
