package com.wfr.learning.in.spring.ioc.container.overview.domain;

/**
 * 用户实体的接口
 *
 * @author wangfarui
 * @since 2022/3/3
 */
public interface IUser {

    default void printUser() {
        System.out.println("this is default user");
    }
}
