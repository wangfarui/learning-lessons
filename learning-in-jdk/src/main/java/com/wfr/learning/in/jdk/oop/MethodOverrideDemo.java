package com.wfr.learning.in.jdk.oop;

/**
 * Java 方法重写示例
 *
 * @author wangfarui
 * @since 2022/4/26
 */
public class MethodOverrideDemo {

    public static void main(String[] args) {
        B b = new B();
        b.packagePrint();
        b.protectedPrint();
        b.publicPrint();
    }


    /**
     * 方法重写时，作用域修饰符的范围大小为：private < package < protected < public
     */
    static class B extends A {

        /**
         * 可以是 package、protected、public
         */
        @Override
        void packagePrint() {
            System.out.println("package print b");
        }

        /**
         * 可以是 protected、public
         */
        @Override
        protected void protectedPrint() {
            System.out.println("protected print b");
        }

        /**
         * 只能是 public
         */
        @Override
        public void publicPrint() {
            System.out.println("public print b");
        }
    }

    static class A {

        private void privatePrint() {
            System.out.println("private print a");
        }

        // package
        void packagePrint() {
            System.out.println("package print a");
        }

        protected void protectedPrint() {
            System.out.println("protected print a");
        }

        public void publicPrint() {
            System.out.println("public print a");
        }

    }

}
