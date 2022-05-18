package com.wfr.learning.in.spring.annotation.aimport;

import com.wfr.learning.in.spring.annotation.domain.Book;
import com.wfr.learning.in.spring.annotation.domain.ThinkingInJavaBook;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义 {@link ImportBeanDefinitionRegistrar} 注册器
 *
 * @author wangfarui
 * @since 2022/5/12
 */
public class BookImportBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (registry.containsBeanDefinition("thinkingInJavaBook")) {
            System.out.println(2);
        }
        if (registry.containsBeanDefinition(ThinkingInJavaBook.BEAN_NAME)) {
            System.out.println("1");
        }
        if (importingClassMetadata.hasAnnotation(Book.class.getName())) {
            System.out.println(importingClassMetadata.getClass().getName());
        }
    }
}
