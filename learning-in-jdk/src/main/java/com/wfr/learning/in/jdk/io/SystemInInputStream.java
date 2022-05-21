package com.wfr.learning.in.jdk.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 基于 {@link System#in} 输入流示例
 *
 * @author wangfarui
 * @since 2022/5/21
 */
public class SystemInInputStream {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null) {
            System.out.println(s);
        }

        System.out.println("finished");
    }
}
