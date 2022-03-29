package com.wfr.learning.in.arithmetic.sort.n2sort;

import java.util.Arrays;

/**
 * 冒泡排序示例
 * <p>
 * 冒泡排序中, 逆序度就是最小交换次数, 无法优化.
 * 但循环次数可以优化, 例如下面的hasSorted参数
 * </p>
 *
 * @author wangfarui
 * @since 2022/1/22
 */
public class BubbleSortDemo {

    public static void main(String[] args) {
        // 有序元素对: 24
        // {8, 10} {8, 9} {5, 6} {5, 10} {5, 7} {5, 9} {4, 6} {4, 10} {4, 7} {4, 9}
        // {6, 10} {6, 7} {6, 9} {1, 3} {1, 10} {1, 2} {1, 7} {1, 9} {3, 10} {3, 7}
        // {3, 9} {2, 7} {2, 9} {7, 9}
        // 满有序度: 10 * (10 - 1) / 2 = 45
        // 逆序度: 45 - 24 = 21

        int[] a = {8, 5, 4, 6, 1, 3, 10, 2, 7, 9};
        int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        // 计算循环执行次数: 42, 计算比较执行次数: 21
        // 冒泡排序中, "计算比较执行次数"就是加有序度的次数, 所以 24 + 21 = 45
        arrayBubbleSort(a);
//        arrayBubbleSort2(a);
        System.out.println("a排序后的结果: " + Arrays.toString(a));

        arrayBubbleSort(b);
        System.out.println("b排序后的结果: " + Arrays.toString(b));

    }

    public static void arrayBubbleSort(int[] a) {
        int len = a.length;
        if (len < 1) {
            return;
        }

        // 计算循环执行次数
        int cycleCount = 0;
        // 计算比较执行次数
        int compareCount = 0;

        for (int i = 0; i < len; i++) {
            boolean hasSorted = false;

            int subLen = len - i - 1;
            for (int j = 0; j < subLen; j++) {
                cycleCount++;
                if (a[j] > a[j + 1]) {
                    compareCount++;
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    hasSorted = true;
                }
            }

            // 当前一次外循环没有需要排序的, 证明当前 [0, subLen] 已经是有序的, 可以提前退出
            if (!hasSorted) {
                break;
            }
        }

        System.out.printf("计算循环执行次数: %s, 计算比较执行次数: %s\n", cycleCount, compareCount);
    }

    /**
     * 其他循环条件的冒泡排序方法
     * <p>
     * hasSorted无效，无法减少循环次数
     * </p>
     *
     * @param a 待排序的数组
     */
    public static void arrayBubbleSort2(int[] a) {
        int len = a.length;

        // 计算循环执行次数
        int cycleCount = 0;
        // 计算比较执行次数
        int compareCount = 0;

        for (int i = 0; i < len - 1; i++) {
            boolean hasSorted = false;

            for (int j = i + 1; j < len; j++) {
                cycleCount++;
                if (a[i] > a[j]) {
                    compareCount++;
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    hasSorted = true;
                }
            }

            if (!hasSorted) {
                break;
            }
        }

        System.out.printf("计算循环执行次数: %s, 计算比较执行次数: %s\n", cycleCount, compareCount);
    }
}
