package com.wfr.learning.in.spring.annotation.aimport;

import com.wfr.learning.in.spring.annotation.domain.AbstractComputerBook;
import com.wfr.learning.in.spring.annotation.domain.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * description
 *
 * @author wangfarui
 * @since 2022/5/12
 */
public class BookAnnotationTypeFilter implements TypeFilter, BeanFactoryAware {

    public static final Class<? extends Annotation> includeAnnotationType = Book.class;

    public static final String includeAnnotationName = Book.class.getName();

    private BeanFactory beanFactory;

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        if (annotationMetadata.hasAnnotation(includeAnnotationName) || annotationMetadata.hasMetaAnnotation(includeAnnotationName)) {
            return true;
        }
        return false;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
