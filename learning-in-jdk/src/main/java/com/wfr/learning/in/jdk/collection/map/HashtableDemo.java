package com.wfr.learning.in.jdk.collection.map;

import java.util.Hashtable;

/**
 * 基于 {@link java.util.Hashtable} 示例
 *
 * @author wangfarui
 * @since 2022/4/24
 */
public class HashtableDemo {

    static Hashtable<Integer, String> map = new Hashtable<>();

    static String PRESENT = "present";

    public static void main(String[] args) throws InterruptedException {
//        insertNode(2);
//        selectNode(2);

        Thread thread1 = batchInsertNode();
        Thread thread2 = batchRemoveNode();
        thread1.join();
        thread2.join();

        // map.size() 数量随机，不一定删除完为0
        // 原因是因为 thread2 执行的 remove 操作，对应的key可能快于了 thread1
        System.out.println(map.size());
    }

    private static Thread batchInsertNode() {
        Thread thread = new Thread(() -> {
            System.out.println("batchInsertNode 开始时间: " + System.nanoTime());
            int n = 10000;
            while (n-- > 0) {
                map.put(n, PRESENT);
            }
            System.out.println("batchInsertNode 结束时间: " + System.nanoTime());
        });

        thread.start();

        return thread;
    }

    private static Thread batchRemoveNode() {
        Thread thread = new Thread(() -> {
            int n = 10000;
            System.out.println("batchRemoveNode 开始时间: " + System.nanoTime());
            while (n-- > 0) {
                map.remove(n);
            }
            System.out.println("batchRemoveNode 结束时间: " + System.nanoTime());
        });

        thread.start();

        return thread;
    }

    private static void selectNode(int key) throws InterruptedException {
        long currentTimeMillis = System.nanoTime();
        System.out.println("selectNode当前时间: " + currentTimeMillis);
        int n = 5;
        while (n-- > 0) {
            System.out.println(n + ": " + map.get(key));
            Thread.sleep(1000L);
        }
    }

    private static void insertNode(int key) {
        long currentTimeMillis = System.nanoTime();
        System.out.println("insertNode前 时间: " + currentTimeMillis);
        new Thread(() -> {
            try {
                Thread.sleep(2000L);
                map.put(key, PRESENT);
                Thread.sleep(1500L);
                map.put(key, "newest");
            } catch (InterruptedException e) {
                System.err.println("睡眠异常");
            }
        }).start();


        long afterTimeMillis = System.nanoTime();
        System.out.println("insertNode后 时间: " + afterTimeMillis);
    }


}
