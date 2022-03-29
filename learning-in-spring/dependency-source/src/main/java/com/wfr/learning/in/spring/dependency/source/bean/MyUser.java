package com.wfr.learning.in.spring.dependency.source.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/3/10
 */
public class MyUser {
    @Value("${my.id}")
    private Long id;

    @Value("${my.name:mywfr}")
    private String username;

    @Value("${my.alias:别名}")
    private String myAlias;

    public String getUsername() {
        return username;
    }
}
