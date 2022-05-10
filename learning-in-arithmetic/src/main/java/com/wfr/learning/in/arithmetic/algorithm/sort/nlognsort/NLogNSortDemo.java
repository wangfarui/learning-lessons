package com.wfr.learning.in.arithmetic.algorithm.sort.nlognsort;

import java.util.Arrays;

/**
 * 时间复杂度为 o(nLogn) 的排序算法示例
 *
 * @author wangfarui
 * @since 2022/5/10
 */
public class NLogNSortDemo {

    public static void main(String[] args) {
        int[] e = {10,8,7,6,4,9,3,2,5,1};
        quickSort(e);
        System.out.println(Arrays.toString(e));
    }

    public static void quickSort(int[] a) {
        int len;
        if((len = a.length) < 1) {
            return;
        }

        partitionQuickSort(a, 0, len-1);
    }

    private static void partitionQuickSort(int[] a, int start, int end) {
        if (start >= end) return;
        int point = a[end];

        int i = start, j = start;

        for (; i<end; i++) {
            if (a[i] < point) {
                if (i == j) {
                    j++;
                } else {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j++] = temp;
                }
            }
        }

        a[end] = a[j];
        a[j] = point;

        if (j - 1 > 0) {
            partitionQuickSort(a, start, j-1);
        }
        partitionQuickSort(a, j+1, end);
    }

    public static void recursiveSort(int[] a) {

    }

    public static void mergeRecursiveSort(int[] a, int start, int end) {

    }

    public static void merge() {

    }
}
