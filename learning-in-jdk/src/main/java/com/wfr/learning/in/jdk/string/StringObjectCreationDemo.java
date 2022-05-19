package com.wfr.learning.in.jdk.string;

/**
 * 基于 {@link String} 对象创建示例
 *
 * @author wangfarui
 * @since 2022/5/19
 */
public class StringObjectCreationDemo {

    public static void main(String[] args) {

        // 编译后，class文件展示为 String s = "abcd"; System.out.println(s);
        String s = "a" + "b" + "c" + "d";
        System.out.println(s);

    }

}
