package com.wfr.learning.in.gadget;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 数独小玩意
 *
 * @author wangfarui
 * @since 2022/6/1
 */
public class SudokuGadget {

    /**
     * 1 2 3 4 5 6 7 8 9
     * 2 3 4 5 6 7 8 9 1
     * 3 4 5 6 7 8 9 1 2
     * 4 5 6 7 8 9 1 2 3
     * 5 6 7 8 9 1 2 3 4
     * 6 7 8 9 1 2 3 4 5
     * 7 8 9 1 2 3 4 5 6
     * 8 9 1 2 3 4 5 6 7
     * 9 1 2 3 4 5 6 7 8
     */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        int[][] a = new int[9][9];
        // 从左到右
        Set<Integer> s0 = new HashSet<>();
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        Set<Integer> s3 = new HashSet<>();
        Set<Integer> s4 = new HashSet<>();
        Set<Integer> s5 = new HashSet<>();
        Set<Integer> s6 = new HashSet<>();
        Set<Integer> s7 = new HashSet<>();
        Set<Integer> s8 = new HashSet<>();

        Queue<Integer> row0 = new LinkedList<>();
        Queue<Integer> row1 = new LinkedList<>();
        Queue<Integer> row2 = new LinkedList<>();
        Queue<Integer> row3 = new LinkedList<>();
        Queue<Integer> row4 = new LinkedList<>();
        Queue<Integer> row5 = new LinkedList<>();
        Queue<Integer> row6 = new LinkedList<>();
        Queue<Integer> row7 = new LinkedList<>();
        Queue<Integer> row8 = new LinkedList<>();

        Queue<Integer> column0 = new LinkedList<>();
        Queue<Integer> column1 = new LinkedList<>();
        Queue<Integer> column2 = new LinkedList<>();
        Queue<Integer> column3 = new LinkedList<>();
        Queue<Integer> column4 = new LinkedList<>();
        Queue<Integer> column5 = new LinkedList<>();
        Queue<Integer> column6 = new LinkedList<>();
        Queue<Integer> column7 = new LinkedList<>();
        Queue<Integer> column8 = new LinkedList<>();

        // 定义已有数据 a
        // ...


        // 从左往右、从上往下
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int t;
                if ((t = a[i][j]) != 0) continue;

            }
        }

    }
}
