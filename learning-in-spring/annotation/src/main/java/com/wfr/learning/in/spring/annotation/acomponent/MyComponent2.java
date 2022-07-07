package com.wfr.learning.in.spring.annotation.acomponent;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link Component} 层级派生性
 *
 * @author wangfarui
 * @since 2022/7/7
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MyComponent
public @interface MyComponent2 {
}
