package com.wfr.learning.in.spring.annotation.aimport;

import com.wfr.learning.in.spring.annotation.domain.AbstractComputerBook;
import com.wfr.learning.in.spring.annotation.domain.Book;
import com.wfr.learning.in.spring.annotation.domain.IBook;
import com.wfr.learning.in.spring.annotation.domain.ThinkingInJavaBook;
import javafx.beans.property.ObjectProperty;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * 关于 {@link Import} 注解的示例
 *
 * @author wangfarui
 * @see org.springframework.context.annotation.Import
 * @see org.springframework.context.annotation.ImportBeanDefinitionRegistrar
 * @since 2022/5/12
 */
@Import(BookImportBeanDefinitionRegistry.class)
@ComponentScan(basePackageClasses = {IBook.class},
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = BookAnnotationTypeFilter.class)})
public class SpringImportDemo {

    public SpringImportDemo() {
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringImportDemo.class);
        applicationContext.getBeanFactory().addBeanPostProcessor(new BookBeanPostProcessor());

        applicationContext.refresh();

        ObjectProvider<IBook> objectProvider = applicationContext.getBeanProvider(IBook.class);
        IBook iBook = objectProvider.getIfAvailable();
        if (iBook != null) {
            System.out.println(iBook.getBookName() + ", " + iBook.getAuthor() + ", " + iBook.getPublishingHouse());
        } else {
            System.out.println("无Book");
        }

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println("非spring内置bean如下: ");
        for (String beanName : beanDefinitionNames) {
            if (beanName.startsWith("org.springframework.context")) {
                continue;
            }
            System.out.println(beanName);
        }

        applicationContext.close();
    }
}
