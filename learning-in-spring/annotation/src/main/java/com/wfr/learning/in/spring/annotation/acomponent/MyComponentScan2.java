package com.wfr.learning.in.spring.annotation.acomponent;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link ComponentScan} 层级派生性
 *
 * @author wangfarui
 * @since 2022/7/7
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages")
    String[] scanBasePackages() default {};

    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackageClasses")
    Class<?>[] scanBasePackageClasses() default {};
}
