package com.wfr.learning.ioc.container.overview.domain;

/**
 * 基于 {@link User} 的特殊用户实体
 *
 * @author wangfarui
 * @since 2022/3/1
 */
public class SpecialUser extends User {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SpecialUser{" +
                "msg='" + msg + '\'' +
                '}' + super.toString();
    }
}
