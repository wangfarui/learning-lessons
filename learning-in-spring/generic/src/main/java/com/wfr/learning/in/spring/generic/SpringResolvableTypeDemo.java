package com.wfr.learning.in.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * 基于 {@link org.springframework.core.ResolvableType} 示例
 *
 * @author wangfarui
 * @since 2022/6/29
 */
public class SpringResolvableTypeDemo {

    public static void main(String[] args) {
        // 工厂创建
        // StringList <- ArrayList <- AbstractList <- List <- Collection
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        resolvableType.getSuperType(); // ArrayList
        resolvableType.getSuperType().getSuperType(); // AbstractList

        System.out.println(resolvableType.asCollection().resolve()); // 获取 Raw Type
        System.out.println(resolvableType.asCollection().resolveGeneric(0)); // 获取泛型参数类型
    }
}
