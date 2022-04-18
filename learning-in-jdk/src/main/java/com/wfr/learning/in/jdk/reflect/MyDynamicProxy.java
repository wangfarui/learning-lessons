package com.wfr.learning.in.jdk.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 基于 JDK动态代理 示例
 */
public class MyDynamicProxy {
    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代码实例
        WelcomeHello proxyHello = (WelcomeHello) Proxy.newProxyInstance(
                HelloImpl.class.getClassLoader(),
                HelloImpl.class.getInterfaces(),
                handler
        );
        // 调用代理方法
        proxyHello.sayHello();
        proxyHello.welcomeBack();
        proxyHello.welcome("Me");
    }
}

interface WelcomeHello extends Hello, Welcome {

}

interface Hello {
    void sayHello();
}

interface Welcome {
    void welcomeBack();

    void welcome(String s);
}

class HelloImpl implements WelcomeHello {
    public void sayHello() {
        System.out.println("Hello World");
    }

    public void welcomeBack() {
        System.out.println("Welcome Back");
    }

    public void welcome(String s) {
        System.out.println("Welcome " + s);
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("Invoking " + method.getName() + ", args: " + Arrays.toString(args));
        Object result = method.invoke(target, args);
        return result;
    }
}
