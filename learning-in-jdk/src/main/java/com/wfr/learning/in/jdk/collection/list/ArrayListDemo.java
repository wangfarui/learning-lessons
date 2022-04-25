package com.wfr.learning.in.jdk.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于 {@link java.util.ArrayList} 示例
 *
 * <pre>总结:</pre>
 * <ol>
 *  <li>ArrayList 的 subList 可以进行数据更改操作，但 ArrayList 本身不能再进行数据更改操作</li>
 * </ol>
 *
 * @author wangfarui
 * @since 2022/4/25
 */
public class ArrayListDemo {

    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        batchAddData();

        subListOperate();
    }

    private static void subListOperate() {
        List<Integer> subList = list.subList(0, 10);
        System.out.println(list.size());
        System.out.println(subList.size());

        subList.add(-1);
//        list.add(0, -2);

        System.out.println(list.size());
        System.out.println(subList.size());
    }

    private static void batchAddData() {
        int n = 0;
        while (n++ < 10000) {
            list.add(n);
        }
    }
}
