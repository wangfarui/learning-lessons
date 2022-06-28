package com.wfr.learning.in.spring.ioc.container.overview.domain;

import com.wfr.learning.in.spring.ioc.container.overview.enums.City;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Properties;

/**
 * 用户实体
 *
 * <p>Bean初始化调用方法的先后顺序为: @PostConstruct > InitializingBean > initMethod()
 * <p>Bean销毁调用方法的先后顺序为: @PreDestroy > DisposableBean > destroyMethod()
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

    private Company company;

    private Properties context;

    private String contextAsText;

    public User() {
        this.name = "default name";
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
    public void doDestroy() {
        System.out.println("User Bean [" + beanName + "] 销毁中...");
    }
}
