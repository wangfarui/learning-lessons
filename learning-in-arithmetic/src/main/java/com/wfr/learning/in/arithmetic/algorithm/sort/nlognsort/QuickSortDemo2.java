package com.wfr.learning.in.arithmetic.algorithm.sort.nlognsort;

import java.util.Arrays;

/**
 * 参考王争老师的快排代码
 *
 * @author wangfarui
 * @since 2022/2/8
 */
public class QuickSortDemo2 {

    // 计算循环执行次数
    static int cycleCount = 0;
    // 合并执行次数
    static int compareCount = 0;

    public static void main(String[] args) {
        int[] a = {8, 5, 4, 6, 1, 3, 10, 2, 7, 9};
        int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        // 计算循环执行次数: 27, 合并执行次数: 21
        quickSort(a, a.length);
        System.out.println("a排序后的结果: " + Arrays.toString(a));
        System.out.printf("计算循环执行次数: %s, 合并执行次数: %s\n", cycleCount, compareCount);

        cycleCount = 0;
        compareCount = 0;

        quickSort(b, b.length);
        System.out.println("b排序后的结果: " + Arrays.toString(b));
        System.out.printf("计算循环执行次数: %s, 合并执行次数: %s\n", cycleCount, compareCount);
    }

    // 快速排序，a是数组，n表示数组的大小
    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n-1);
    }

    // 快速排序递归函数，p,r为下标
    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); // 获取分区点
        quickSortInternally(a, p, q-1);
        quickSortInternally(a, q+1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for(int j = p; j < r; ++j) {
            cycleCount++;
            if (a[j] < pivot) {
                compareCount++;
                if (i == j) {
                    ++i;
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }

//        int tmp = a[i];
//        a[i] = a[r];
        a[r] = a[i];
        a[i] = pivot;

//        System.out.println("i=" + i);
        return i;
    }
}
