package com.wfr.learning.in.spring.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 自定义 {@link ConditionalGenericConverter} 实现
 * <p>{@link java.util.Properties} -> {@link String} 的类型转换
 *
 * @author wangfarui
 * @since 2022/6/28
 */
public class PropertiesToStringConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Properties.class.equals(sourceType.getObjectType()) && String.class.equals(targetType.getObjectType());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair convertiblePair = new ConvertiblePair(Properties.class, String.class);
        return Collections.singleton(convertiblePair);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        Properties properties = (Properties) source;
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" = ").append(entry.getValue()).append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }
}
