package com.wfr.learning.in.jdk.collection.list;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 基于 {@link java.util.concurrent.CopyOnWriteArrayList} 示例
 *
 * <pre>总结:</pre>
 * <ol>
 *     <li>CopyOnWriteArrayList 的 subList 可以进行数据更改操作，但 CopyOnWriteArrayList 本身不能再进行数据更改操作</li>
 * </ol>
 *
 * @author wangfarui
 * @since 2022/4/25
 */
public class CopyOnWriteArrayListDemo {

    static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

    /**
     * 该示例不能说明 {@link Hashtable} 不是线程安全的，即使 thread1 和 thread2 之间有互斥关系。<br/>
     * 原因是因为 thread2 执行的 remove 操作，可能快于了 thread1 的 add 操作
     */
    public static void main(String[] args) throws InterruptedException {
//        insertNode(2);
//        selectNode(2);

//        Thread thread1 = batchInsertNode();
//        Thread thread2 = batchRemoveNode();
//        thread1.join();
//        thread2.join();
//
//        // map.size() 数量随机，不一定删除完为0
//        //
//        System.out.println(list.size());

        Thread thread = batchInsertNode();
        thread.join();
        syncSubList();
    }

    /**
     * 测试 subList 创建的对象是否也为副本，是否不会影响原 CopyOnWriteArrayList
     *
     * <p>结果证明: CopyOnWriteArrayList.subList 与 ArrayList.subList 实现基本一致，对 subList 进行的数据操作会影响到原 CopyOnWriteArrayList
     * <p>通过源码也可知，CopyOnWriteArrayList 在 subList 创建对象时，是将 this 传给了 COWSubList 对象
     */
    private static void syncSubList() {
        int toIndex = 10;

        System.out.println(list.size());
        List<Integer> subList = list.subList(0, toIndex);
        System.out.println(subList.size());

        subList.add(-1);

        // 非法操作，会导致 subList 出现 ConcurrentModificationException 异常
        // 原因是 CopyOnWriteArrayList 在add后，会set新副本为array，与COWSubList中的expectedArray实例对象不一致
        // list.add(0, -2);

        System.out.println(subList.size());
        System.out.println(list.size());

        System.out.println(subList.get(subList.size() - 1));
        System.out.println(list.get(toIndex));
    }


    private static Thread batchInsertNode() {
        Thread thread = new Thread(() -> {
            System.out.println("batchInsertNode 开始时间: " + System.nanoTime());
            int n = 10000;
            while (n-- > 0) {
                list.add(n);
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
                list.remove(Integer.valueOf(n));
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
            System.out.println(n + ": " + list.get(key));
            Thread.sleep(1000L);
        }
    }

    private static void insertNode(int key) {
        long currentTimeMillis = System.nanoTime();
        System.out.println("insertNode前 时间: " + currentTimeMillis);
        new Thread(() -> {
            try {
                Thread.sleep(2000L);
                list.add(key);
                Thread.sleep(1500L);
                list.add(key);
            } catch (InterruptedException e) {
                System.err.println("睡眠异常");
            }
        }).start();


        long afterTimeMillis = System.nanoTime();
        System.out.println("insertNode后 时间: " + afterTimeMillis);
    }
}
