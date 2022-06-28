package com.wfr.learning.in.spring.conversion;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 自定义 {@link PropertyEditorRegistrar} 实现
 *
 * @author wangfarui
 * @since 2022/6/28
 */
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(User.class, "context", new StringToPropertiesPropertyEditor());
    }
}
