package com.wfr.learning.in.jdk.classloader;

/**
 * 基于Java {@link ClassLoader} 类加载器示例
 *
 * @author wangfarui
 * @since 2022/4/27
 */
public class ClassLoaderDemo {
    static String className = "com.wfr.learning.in.jdk.concurrent.SynchronizedDemo";

    public static void main(String[] args) throws ClassNotFoundException {
        printNativeClassLoader();

        printNativeClassForName();
    }

    private static void printNativeClassForName() throws ClassNotFoundException {
        // Class.forName 是调用本地方法 forName0 ，获取到的Class对象也是同一个
        Class<?> classForName = Class.forName(className);
        System.out.println(classForName);
    }

    private static void printNativeClassLoader() throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader: " + systemClassLoader);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> loadClass = classLoader.loadClass(className);
        System.out.println(loadClass);
        // 再次加载相同类型的Class时，直接本地方法 findLoadedClass0 获取到, 获取到的Class对象也是同一个
        Class<?> loadClass1 = classLoader.loadClass(className);

        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }
}
