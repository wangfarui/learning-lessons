package com.wfr.learning.in.spring.annotation.aenable;

import com.wfr.learning.in.spring.annotation.aimport.HelloWorldImportBeanDefinitionRegistry;
import com.wfr.learning.in.spring.annotation.aimport.HelloWorldImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 激活 "HelloWorld" 模块注解
 *
 * @author wangfarui
 * @since 2022/7/7
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({HelloWorldImportBeanDefinitionRegistry.class, HelloWorldImportSelector.class})
public @interface EnableHelloWorld {
}
