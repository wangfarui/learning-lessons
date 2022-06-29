package com.wfr.learning.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Java 泛型示例
 *
 * @author wangfarui
 * @since 2022/6/29
 */
public class JavaGenericDemo {

    public static void main(String[] args) {

        collectionGenericDemo();
    }

    /**
     * Java {@link java.util.Collection} 泛型示例
     */
    public static void collectionGenericDemo() {
        Collection<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        // 编译时出错
        // list.add(1);

        System.out.println(list);

        // 不指定泛型时，可以逃过编译器的强类型检查，并且在运行时正常执行
        Collection collection = list;
        collection.add(1);

        System.out.println(list);
        /**
         * 输出如下：
         * class java.lang.String: a
         * class java.lang.String: b
         * class java.lang.Integer: 1
         */
        // 证明一个集合可以放不同类型的数据
        for (Object o : list) {
            System.out.println(o.getClass() + ": " + o);
        }
    }
}
