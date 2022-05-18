package com.wfr.learning.in.spring.annotation.domain;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记 {@link IBook} 实现的类
 *
 * @author wangfarui
 * @since 2022/5/12
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Component
public @interface Book {

//    @AliasFor(annotation = Component.class)
    String value() default "";

//    @AliasFor(annotation = Component.class, attribute = "value")
    String name() default "";

    String author() default "";

    String publishingHouse() default "";
}
