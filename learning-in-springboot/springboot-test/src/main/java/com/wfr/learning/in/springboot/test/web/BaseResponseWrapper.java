package com.wfr.learning.in.springboot.test.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 基础响应对象包装类型注解
 * <p>一般用于控制响应对象是否需要包装、包装对象的参数默认值</p>
 *
 * @author wangfarui
 * @see BaseResponse
 * @since 2022/6/24
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseResponseWrapper {

    boolean useWrapper() default true;

    int code() default 200;

    String message() default "Success";
}
