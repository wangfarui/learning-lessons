package com.wfr.learning.in.arithmetic.algorithm.sort.nlognsort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author wangfarui
 * @since 2022/2/8
 */
public class MergeSortDemo {

    // 计算循环执行次数
    static int cycleCount = 0;
    // 合并执行次数
    static int compareCount = 0;

    public static void main(String[] args) {
        int[] a = {8, 5, 4, 6, 1, 3, 10, 2, 7, 9};
        int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        // 计算循环执行次数: 27, 合并执行次数: 21
        arrayMergeSort(a);
        System.out.println("a排序后的结果: " + Arrays.toString(a));
        System.out.printf("计算循环执行次数: %s, 合并执行次数: %s\n", cycleCount, compareCount);

        cycleCount = 0;
        compareCount = 0;

        arrayMergeSort(b);
        System.out.println("b排序后的结果: " + Arrays.toString(b));
        System.out.printf("计算循环执行次数: %s, 合并执行次数: %s\n", cycleCount, compareCount);
    }

    public static void arrayMergeSort(int[] a) {
        int len = a.length;
        if (len < 1) {
            return;
        }

        sort(a, 0, len - 1);
    }

    public static void sort(int[] a, int start, int end) {
        if (start >= end) return;

        int mid = start + (end - start) / 2;

        sort(a, start, mid);
        sort(a, mid + 1, end);

        merge(a, start, mid, end);
    }

    public static void merge(int[] a, int start, int mid, int end) {
        int tempLen = end - start + 1;
        int[] temp = new int[tempLen];

        int t = 0;
        int i = start;
        int j = mid + 1;
        // 使用&&并集，只确保一个区间的数据已经处理完毕
        while (i <= mid && j <= end) {
            cycleCount++;
            if (a[i] < a[j]) {
                temp[t++] = a[i++];
            } else {
                temp[t++] = a[j++];
            }
        }

        // 默认左区间未处理完毕
        int tempStart = i; // [i, mid]表示未处理的左区间
        int tempEnd = mid;
        // 判断是否是右区间未处理完毕
        if (j <= end) {
            tempStart = j; // [j, end]表示未处理的右区间
            tempEnd = end;
        }

        // 将未处理的数据默认填充到临时数组后面
        while (tempStart <= tempEnd) {
            cycleCount++;
            temp[t++] = a[tempStart++];
        }

        for (int k = 0; k < t; k++) {
            compareCount++;
            a[start++] = temp[k];
        }
    }

}
