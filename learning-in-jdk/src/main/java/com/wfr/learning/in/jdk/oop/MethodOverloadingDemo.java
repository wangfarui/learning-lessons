package com.wfr.learning.in.jdk.oop;

/**
 * Java 方法重载示例
 * <ul>
 *  <li>方法重载，只在乎形参是否相同，修饰符作用域、返回类型、静态等是可变可不变的</li>
 * </ul>
 *
 * @author wangfarui
 * @since 2022/4/26
 */
public class MethodOverloadingDemo {

    private void print() {

    }

    void print(String str) {

    }

    protected void print(Integer i) {

    }

    public void print(char c) {

    }

    static void print(String s1, String s2) {

    }

    static void print(String s1, int i) {

    }


}
