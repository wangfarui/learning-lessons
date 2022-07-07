package com.wfr.learning.in.spring.annotation.aimport;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * "HelloWorld" 模块的 {@link ImportSelector}
 *
 * @author wangfarui
 * @since 2022/7/7
 */
public class HelloWorldImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.wfr.learning.in.spring.annotation.domain.TestComponent"};
    }
}
