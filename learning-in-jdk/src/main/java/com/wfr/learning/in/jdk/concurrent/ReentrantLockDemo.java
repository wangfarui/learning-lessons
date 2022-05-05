package com.wfr.learning.in.jdk.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 {@link java.util.concurrent.locks.ReentrantLock} 示例
 *
 * @author wangfarui
 * @since 2022/5/5
 */
public class ReentrantLockDemo {

    private static Unsafe unsafe;

    private static long modCountOffset;

    private final ReentrantLock lock = new ReentrantLock();

    private volatile int modCount;

    private volatile boolean succeed;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            modCountOffset = unsafe.objectFieldOffset(ReentrantLockDemo.class.getDeclaredField("modCount"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void incrementCount() throws InterruptedException {
        lock.lock();
        try {
            int count = getModCount();
            int nextCount = count + 1;

            Thread.sleep(500L);
            unsafe.compareAndSwapInt(this, modCountOffset, count, nextCount);
            if (nextCount == 10) {
                succeed = true;
            }
        } finally {
            lock.unlock();
        }
    }

    public int getModCount() {
        return modCount;
    }

    /**
     * 测试多线程并发计数
     */
    public static void testMultiThreadConcurrentCount() throws InterruptedException {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Thread[] threads = new Thread[10];

        for(int i=0; i<10; i++) {
            threads[i] = new Thread(() -> {
                try {
                    demo.incrementCount();
                } catch (InterruptedException e) {
                    System.out.println("increment count error");
                }
            });
        }

        for(int i=0; i<10; i++) {
            threads[i].start();
        }

        Thread printThread = new Thread(() -> {
            while (!demo.succeed) {
                try {
                    Thread.sleep(400L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("now modCount is " + demo.getModCount());
            }
        });

        printThread.start();

        for(int i=0; i<10; i++) {
            threads[i].join();
        }

        System.out.println("demo.modCount: " + demo.getModCount());
    }

    public static void main(String[] args) throws InterruptedException {
        testMultiThreadConcurrentCount();
    }
}
