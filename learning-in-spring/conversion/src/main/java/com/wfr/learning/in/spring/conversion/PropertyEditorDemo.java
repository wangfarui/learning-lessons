package com.wfr.learning.in.spring.conversion;

import java.beans.PropertyEditor;

/**
 * 基于 {@link java.beans.PropertyEditor} 示例
 *
 * @author wangfarui
 * @since 2022/6/28
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        String text = "name = wfr";

        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getAsText());
        System.out.println(propertyEditor.getValue());
    }
}
