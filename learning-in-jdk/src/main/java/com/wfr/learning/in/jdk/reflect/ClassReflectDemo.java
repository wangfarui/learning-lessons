package com.wfr.learning.in.jdk.reflect;

import com.wfr.learning.in.jdk.domain.Student;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Java Class 反射示例
 *
 * @author wangfarui
 * @since 2022/4/18
 */
public class ClassReflectDemo extends Student{


    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {

        accessAccessible();
    }

    /**
     * 基于 {@link java.lang.reflect.AccessibleObject} override属性访问权限的示例
     * <p>示例总结如下:</p>
     * <ul>
     *  <li>Class下的Field、Method等继承了AccessibleObject类的对象，默认override都为false</li>
     *  <li>每个AccessibleObject对象都是不同实例对象，并且每次Class.getXxx获取的对象以及内部数组对象都不是同一个，例如Class.getMethods等</li>
     * </ul>
     */
    private static void accessAccessible() throws IllegalAccessException, InvocationTargetException {
        Student student = new ClassReflectDemo();
        Field[] declaredFields = Student.class.getDeclaredFields();
        Field[] declaredFields2 = Student.class.getDeclaredFields();
        for (Field field : declaredFields) {
            if ("name".equals(field.getName())) {
                field.setAccessible(true);
                field.set(student, "wfr");
            }
            if ("id".equals(field.getName())) {
                field.setAccessible(true);
                field.set(student, 1L);
            }
        }
        Method[] methods = Student.class.getMethods();
        Method[] methods2 = Student.class.getMethods();
        for (Method method : methods) {
            if ("setId".equals(method.getName())) {
                method.invoke(student, 2L);
            }
            if ("setName".equals(method.getName())) {
                method.invoke(student, "wangfarui");
            }
        }
        System.out.println(student);
    }
}
