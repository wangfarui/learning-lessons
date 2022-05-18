package com.wfr.learning.in.jdk.io;

import java.io.*;
import java.util.Arrays;

/**
 * Java IO流的关闭顺序
 *
 * @author wangfarui
 * @since 2022/5/18
 */
public class IOStreamCloseOrderDemo {

    public static void main(String[] args) {
        String filePath = "D:\\workspaces\\wfr\\learning-lessons\\learning-in-jdk\\src\\main\\resources\\lib\\char_file.txt";

        // 见 IOStreamCloseOrderDemo 编译的class文件
        // 顺序基本是遵循“先开后关”
        try(InputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
            int read = inputStreamReader.read();
            System.out.println(read);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
