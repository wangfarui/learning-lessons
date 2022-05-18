package com.wfr.learning.in.spring.annotation.domain;

/**
 * Java编程思想
 *
 * @author wangfarui
 * @since 2022/5/12
 */
@Book(name = ThinkingInJavaBook.BEAN_NAME)
public class ThinkingInJavaBook extends AbstractComputerBook {

    public static final String BOOK_NAME = "Thinking in Java";

    public static final String BEAN_NAME = "ThinkingInJava";

    @Override
    public String getBookName() {
        String name = getName();
        return name != null ? name : BOOK_NAME;
    }
}
