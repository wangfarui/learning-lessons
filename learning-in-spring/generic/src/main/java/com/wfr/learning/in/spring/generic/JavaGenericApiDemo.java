package com.wfr.learning.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Java 泛型API示例
 *
 * @author wangfarui
 * @since 2022/6/29
 */
public class JavaGenericApiDemo {

    public static void main(String[] args) {
        Class intClass = int.class;

        Class arrayClass1 = int[].class;
        Class arrayClass2 = Object[].class;

        Class rawClass = String.class;

        /**
         * int
         * [I
         * [Ljava.lang.Object;
         * java.lang.String
         */
        System.out.println(intClass.getName());
        System.out.println(arrayClass1.getName());
        System.out.println(arrayClass2.getName());
        System.out.println(rawClass.getName());

        System.out.println("==================");

        // 最终实现类为 ParameterizedTypeImpl
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println(parameterizedType.toString());

        // <E>
        Type[] typeVariables = parameterizedType.getActualTypeArguments();

        Stream.of(typeVariables)
                .map(TypeVariable.class::cast) // Type -> TypeVariable
                .forEach(System.out::println);
    }
}
