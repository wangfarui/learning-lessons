package com.wfr.learning.in.arithmetic.sort.n2sort;

import java.util.Arrays;

/**
 * 插入排序示例
 *
 * @author wangfarui
 * @since 2022/1/22
 */
public class InsertionSortDemo {

    public static void main(String[] args) {
        // 有序元素对: 24
        // {8, 10} {8, 9} {5, 6} {5, 10} {5, 7} {5, 9} {4, 6} {4, 10} {4, 7} {4, 9}
        // {6, 10} {6, 7} {6, 9} {1, 3} {1, 10} {1, 2} {1, 7} {1, 9} {3, 10} {3, 7}
        // {3, 9} {2, 7} {2, 9} {7, 9}
        // 满有序度: 10 * (10 - 1) / 2 = 45
        // 逆序度: 45 - 24 = 21
        int[] a = {8, 5, 4, 6, 1, 3, 10, 2, 7, 9};
        int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        // 计算循环执行次数: 27, 计算比较执行次数: 21
        // 插入排序中, "计算比较执行次数"就是加有序度的次数, 所以 24 + 21 = 45
        arrayInsertionSort(a);
        System.out.println("a排序后的结果: " + Arrays.toString(a));

        arrayInsertionSort(b);
        System.out.println("b排序后的结果: " + Arrays.toString(b));

    }

    public static void arrayInsertionSort(int[] a) {
        int len = a.length;
        if (len < 1) {
            return;
        }

        // 计算循环执行次数
        int cycleCount = 0;
        // 计算比较执行次数
        int compareCount = 0;

        for (int i = 1; i < len; i++) {

            int temp = a[i];
            int j = i - 1;

            for (; j >= 0; j--) {
                cycleCount++;

                if (temp < a[j]) {
                    compareCount++;

                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }

            a[j + 1] = temp;
        }

        System.out.printf("计算循环执行次数: %s, 计算比较执行次数: %s\n", cycleCount, compareCount);

    }
}
