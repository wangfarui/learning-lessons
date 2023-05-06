package com.wfr.learning.in.jdk.classloader;

import com.wfr.learning.in.jdk.domain.Student;

/**
 * 类初始化示例
 * 类初始化过程大致分为：静态初始化 -> 父类初始化 -> 子类初始化
 *
 * @author Wray
 * @since 2023/5/6
 */
public class ClassInitializationDemo {

    public static void main(String[] args) {

        System.out.println("打印 Student 类初始化过程: ");
        /**
         * 1、 new 用到了Student.class,所以会先找到Student.class文件，并加载到内存中(用到类中的内容类就会被加载)
         *
         * 2、执行该对象的static代码块(静态初始块)。(如果有的话，给Student.class类进行初始化)
         *
         * 3、在堆内存中开辟空间，分配内存地址
         *
         * 4、在堆内存中建立对象特有属性，并进行默认初始化
         *
         * 5、对属性进行显示初始化(声明成员属性并赋值)
         *
         * 6、执行普通初始块
         *
         * 7、执行构造函数
         *
         * 8、将内存地址赋值给栈内存中的jack变量
         */
        Student student = new Student();

    }
}
