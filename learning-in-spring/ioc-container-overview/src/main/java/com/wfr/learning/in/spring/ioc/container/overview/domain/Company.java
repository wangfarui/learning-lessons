package com.wfr.learning.in.spring.ioc.container.overview.domain;

/**
 * 公司类
 *
 * @author wangfarui
 * @since 2022/6/20
 */
public class Company {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
