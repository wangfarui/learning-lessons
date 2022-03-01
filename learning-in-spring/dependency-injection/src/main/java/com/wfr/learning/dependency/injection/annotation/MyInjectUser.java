package com.wfr.learning.dependency.injection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 基于 {@link InjectUser} 注解的自定义注入注解
 *
 * @author wangfarui
 * @since 2022/3/1
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@InjectUser
public @interface MyInjectUser {
}
