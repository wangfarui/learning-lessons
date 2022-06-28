package com.wfr.learning.in.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * String -> Properties {@link java.beans.PropertyEditor}
 * <p>将字符串转为 Properties 存至 value 变量中
 *
 * @author wangfarui
 * @since 2022/6/28
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" = ").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        setValue(properties);
    }
}
