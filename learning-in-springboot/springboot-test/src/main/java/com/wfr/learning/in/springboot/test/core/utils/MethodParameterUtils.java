package com.wfr.learning.in.springboot.test.core.utils;

import com.wfr.learning.in.springboot.test.web.BaseResponseWrapper;
import org.springframework.core.MethodParameter;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * {@link MethodParameter} 类的封装工具类
 *
 * @author wangfarui
 * @since 2022/6/24
 */
public abstract class MethodParameterUtils {

    @SuppressWarnings("unchecked")
    public static <T extends Annotation> T obtainResponseWrapper(MethodParameter returnType, Class<T> aClass) {
        Annotation[] methodAnnotations = returnType.getMethodAnnotations();
        Optional<Annotation> responseWrapperAnnotation = Stream.of(methodAnnotations)
                .filter(annotation -> annotation.annotationType().isAssignableFrom(aClass))
                .findFirst();
        if (responseWrapperAnnotation.isPresent()) {
            return (T) responseWrapperAnnotation.get();
        }

        Annotation[] classAnnotations = returnType.getDeclaringClass().getAnnotations();
        responseWrapperAnnotation = Stream.of(classAnnotations)
                .filter(annotation -> annotation.annotationType().isAssignableFrom(BaseResponseWrapper.class))
                .findFirst();
        return (T) responseWrapperAnnotation.orElse(null);
    }
}
