package com.wfr.learning.in.arithmetic.algorithm.sort.n2sort;

import java.util.Arrays;

/**
 * 时间复杂度为0(n²)的排序算法示例
 *
 * @author wangfarui
 * @since 2022/5/9
 */
public class N2SortDemo {

    public static void main(String[] args) {
        int[] a = {10,8,7,6,4,9,3,2,5,1};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));

        int[] b = {10,8,7,6,4,9,3,2,5,1};
        insertionSort(b);
        System.out.println(Arrays.toString(b));

        int[] c = {10,8,7,6,4,9,3,2,5,1};
        selectionSort(c);
        System.out.println(Arrays.toString(c));
    }


    public static void bubbleSort(int[] a) {
        int len;
        if ((len = a.length) < 1) {
            return;
        }

        for (int i=0; i<len; i++) {
            boolean hasSorted = false;

            int subLen = len-i-1;
            for (int j=0; j<subLen; j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    hasSorted = true;
                }
            }

            if (!hasSorted) {
                break;
            }
        }
    }

    public static void insertionSort(int[] a) {
        int len;
        if ((len = a.length) < 1) {
            return;
        }

        for (int i=1; i<len; i++) {
            int temp = a[i];

            int j=i-1;
            for (; j>=0; j--) {
                if (a[j] > temp) {
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }

            a[j+1] = temp;
        }
    }

    public static void selectionSort(int[] a) {
        int len;
        if ((len = a.length) < 1) {
            return;
        }

        for (int i=0; i<len; i++) {
            int index = i;

            for (int j=i+1; j<len; j++) {
                if (a[index] > a[j]) {
                    index = j;
                }
            }

            if (i != index) {
                int temp = a[i];
                a[i] = a[index];
                a[index] = temp;
            }
        }
    }

}
