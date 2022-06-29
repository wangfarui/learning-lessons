package com.wfr.learning.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/6/29
 */
public class SpringGenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        displayReturnTypeGenericInfo(SpringGenericTypeResolverDemo.class, Comparable.class, "getString");

        displayReturnTypeGenericInfo(SpringGenericTypeResolverDemo.class, List.class, "getList");

        displayReturnTypeGenericInfo(SpringGenericTypeResolverDemo.class, List.class, "getStringList");

        // 具备 ParameterizedType 返回，否则 null

        // TypeVariable
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }

    public static String getString() {
        return null;
    }

    public static ArrayList<Object> getList() { // 泛型参数类型具体化
        return null;
    }

    public static StringList getStringList() {
        return null;
    }

    public static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, parameterTypes);

        Class<?> resolveReturnType = GenericTypeResolver.resolveReturnType(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s\n", method.getName(), genericIfc.getSimpleName(), resolveReturnType);

        Class<?> resolveReturnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s\n", method.getName(), genericIfc.getSimpleName(), resolveReturnTypeArgument);



    }
}
