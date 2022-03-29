package com.wfr.learning.in.arithmetic.sort.nlognsort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author wangfarui
 * @since 2022/2/8
 */
public class QuickSortDemo {

    // 11 8 3 9 7 1 2 5
    // 11 8 3 9 7 1 2 5
    // 3 8 11 9 7 1 2 5
    // 3 1 11 9 7 8 2 5
    // 3 1 2 9 7 8 11 5
    // 3 1 2 5 7 8 11 9
    // 3 1 2  |  7 8 11 9
    // 1 3 2
    // 1 2 3
    //          7 8 9 11

    // 计算循环执行次数
    static int cycleCount = 0;
    // 合并执行次数
    static int compareCount = 0;

    public static void main(String[] args) {
//        int[] a = {11, 8, 3, 9, 7, 1, 2, 5};
        int[] a = {8, 5, 4, 6, 1, 3, 10, 2, 7, 9};
        int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        // 计算循环执行次数: 25, 计算比较执行次数: 16
        arrayQuickSort(a);
        System.out.println("a排序后的结果: " + Arrays.toString(a));
        System.out.printf("计算循环执行次数: %s, 计算比较执行次数: %s\n", cycleCount, compareCount);

        cycleCount = 0;
        compareCount = 0;

        arrayQuickSort(b);
        System.out.println("b排序后的结果: " + Arrays.toString(b));
        System.out.printf("计算循环执行次数: %s, 计算比较执行次数: %s\n", cycleCount, compareCount);
    }

    public static void arrayQuickSort(int[] a) {
        int len = a.length;
        if (len < 1) {
            return;
        }

        sort(a, 0, len - 1);
    }

    public static void sort(int[] a, int start, int end) {
        if (start >= end) return;

        int point = a[end];
        int i = start;
        int j = start;

        for (; j < end; j++) {
            cycleCount++;
            if (a[j] < point) {
                compareCount++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }
        }

        a[end] = a[i];
        a[i] = point;

        if (i-1 > 0) {
            sort(a, start, i-1);
        }
        sort(a, i+1, end);
    }

}
