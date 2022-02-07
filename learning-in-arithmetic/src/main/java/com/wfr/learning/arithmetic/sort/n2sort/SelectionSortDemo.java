package com.wfr.learning.arithmetic.sort.n2sort;

import java.util.Arrays;

/**
 * 选择排序示例
 *
 * @author wangfarui
 * @since 2022/1/22
 */
public class SelectionSortDemo {

    public static void main(String[] args) {
        // 有序元素对: 24
        // {8, 10} {8, 9} {5, 6} {5, 10} {5, 7} {5, 9} {4, 6} {4, 10} {4, 7} {4, 9}
        // {6, 10} {6, 7} {6, 9} {1, 3} {1, 10} {1, 2} {1, 7} {1, 9} {3, 10} {3, 7}
        // {3, 9} {2, 7} {2, 9} {7, 9}
        // 满有序度: 10 * (10 - 1) / 2 = 45
        // 逆序度: 45 - 24 = 21
        int[] a = {8, 5, 4, 6, 1, 3, 10, 2, 7, 9};
        int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        // 计算循环执行次数: 45, 计算比较执行次数: 13
        // 选择排序中, 因为没有条件终止,所以循环执行次数是固定的. 此外, 选择排序不属于稳定排序算法
        arraySelectionSort(a);
        System.out.println("a排序后的结果: " + Arrays.toString(a));

        arraySelectionSort(b);
        System.out.println("b排序后的结果: " + Arrays.toString(b));

    }

    public static void arraySelectionSort(int[] a) {
        int len = a.length;
        if (len < 1) {
            return;
        }

        // 计算循环执行次数
        int cycleCount = 0;
        // 计算比较执行次数
        int compareCount = 0;

        for (int i = 0; i < len - 1; i++) {

            int minIndex = i;
            for (int j = i+1; j < len; j++) {
                cycleCount++;

                if (a[minIndex] > a[j]) {
                    compareCount++;
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = a[minIndex];
                a[minIndex] = a[i];
                a[i] = temp;
            }
        }

        System.out.printf("计算循环执行次数: %s, 计算比较执行次数: %s\n", cycleCount, compareCount);
    }
}
