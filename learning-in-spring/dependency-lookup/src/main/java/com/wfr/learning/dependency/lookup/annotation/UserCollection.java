package com.wfr.learning.dependency.lookup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 集合类型Bean的注解
 *
 * @author wangfarui
 * @since 2022/2/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UserCollection {

    String value() default "";
}
