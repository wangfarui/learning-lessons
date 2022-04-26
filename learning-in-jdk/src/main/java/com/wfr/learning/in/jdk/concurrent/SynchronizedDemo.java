package com.wfr.learning.in.jdk.concurrent;

import java.util.Date;

/**
 * 基于 Java synchronized 示例
 *
 * @author wangfarui
 * @since 2022/4/26
 */
public class SynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();

        synchronizedDemo.synchronizeInstanceMethod();
    }

    /**
     * 测试实例对象不同的同步方法是否公用实例方法一把锁，还是每个实例方法是一把锁
     * <p>
     * 结果证明：synchronized 锁实例方法时，锁的是实例对象。
     */
    private void synchronizeInstanceMethod() throws InterruptedException {
        ConcurrentObject concurrentObject = new ConcurrentObject();
        Thread thread1 = new Thread(concurrentObject::syncIncrementCount);

        Thread thread2 = new Thread(() -> {
            int count = concurrentObject.syncGetCount();
            System.out.println("count: " + count);
        });

        System.out.println(new Date());
        thread1.start();
        Thread.sleep(1000L);
        System.out.println(new Date());
        thread2.start();
        System.out.println(new Date());
    }


    static class ConcurrentObject {
        // 如果count为Integer，默认为null，调用count++会报错空指针
        private int count;

        public synchronized int syncGetCount() {
            System.out.println("syncGetCount");
            return this.count;
        }

        public synchronized void syncSetCount(int count) {
            System.out.println("syncSetCount");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.count = count;
        }

        public synchronized void syncIncrementCount() {
            System.out.println("syncIncrementCount");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.count++;
        }
    }
}
