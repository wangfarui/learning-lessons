package com.wfr.learning.ioc.container.overview.domain;

import com.wfr.learning.ioc.container.overview.enums.City;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class User implements IUser, BeanNameAware {

    private Long id;

    private String name;

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    private String beanName;

    public User() {
    }

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
        return createUser(1L);
    }

    public static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("wangfarui");
        user.setCity(City.WUHAN);
        return user;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @PostConstruct
    public void init() {
        System.out.println("User Bean [" + beanName + "] 初始化中...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("User Bean [" + beanName + "] 销毁中...");
    }
}
