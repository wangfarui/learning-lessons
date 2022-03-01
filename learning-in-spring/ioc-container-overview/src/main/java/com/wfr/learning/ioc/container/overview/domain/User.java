package com.wfr.learning.ioc.container.overview.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户实体
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

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(Long id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("wangfarui");
        user.setCity(City.WUHAN);
        return user;
    }
}
