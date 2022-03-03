package com.wfr.learning.ioc.container.overview.domain;

import com.wfr.learning.ioc.container.overview.annotation.Super;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 基于 {@link User} 的超级用户实体
 *
 * @author wangfarui
 * @since 2022/1/13
 */
@Super
@Getter
@Setter
public class SuperUser extends User{

    private String address;

    public SuperUser() {}

    public SuperUser(Long id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' + " " +
                super.toString() + '}';
    }

    @Override
    public void printUser() {
        System.out.println("this is super user");
    }
}
