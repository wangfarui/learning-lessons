package com.wfr.learning.in.jdk.domain;

/**
 * 人类
 *
 * @author Wray
 * @since 2023/5/6
 */
public class Person {

    private static String PERSON_NAME = "person";

    /**
     * 初始化年龄0岁
     */
    private Integer age = 0;

    static {
        System.out.println("Person static method");
    }

    public Person() {
        System.out.println("Person construction method。age: " + this.age);
        System.out.println("PERSON_NAME: " + PERSON_NAME);
    }
}
