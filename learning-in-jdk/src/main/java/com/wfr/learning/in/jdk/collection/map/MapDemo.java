package com.wfr.learning.in.jdk.collection.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map 示例
 *
 * @author wangfarui
 * @since 2022/4/24
 */
public class MapDemo {

    public static void main(String[] args) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        Map<Integer, Integer> treeMap = new TreeMap<>();
        Map<Integer, Integer> hashtable = new Hashtable<>();
        Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        List<Map<Integer, Integer>> list = new ArrayList<>();
        list.add(hashMap);
        list.add(linkedHashMap);
        list.add(treeMap);
        list.add(hashtable);
        list.add(concurrentHashMap);

        for (Map<Integer, Integer> map : list) {
            map.put(1, 10);

            /**
             * {@link Hashtable}、{@link ConcurrentHashMap} 不允许 key、value 为空
             */
            if (Hashtable.class.isAssignableFrom(map.getClass()) || ConcurrentHashMap.class.isAssignableFrom(map.getClass())) {
                continue;
            }

            map.put(2, null);
            map.put(3, null);

            /**
             * {@link TreeMap} 无比较器时，默认key不允许为null；有比较器时，比较器需要自己判断null值情况
             */
            if (!(map instanceof TreeMap)) {
                map.put(null, 4);
                map.put(null, 5);
                map.put(null, null);
                map.put(null, null);
            }
        }

        /**
         * Map type: java.util.HashMap, size: 4
         * Map type: java.util.LinkedHashMap, size: 4
         * Map type: java.util.TreeMap, size: 3
         * Map type: java.util.Hashtable, size: 1
         * Map type: java.util.concurrent.ConcurrentHashMap, size: 1
         */
        for (Map<Integer, Integer> map : list) {
            System.out.printf("Map type: %s, size: %d\n", map.getClass().getName(), map.size());
        }

        for (Map<Integer, Integer> map : list) {
            System.out.println(map.getClass().getName());
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }
}
