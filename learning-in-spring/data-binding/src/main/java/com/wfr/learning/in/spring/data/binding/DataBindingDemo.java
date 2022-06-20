package com.wfr.learning.in.spring.data.binding;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于 {@link org.springframework.validation.DataBinder} 的示例
 *
 * @author wangfarui
 * @since 2022/6/20
 */
public class DataBindingDemo {

    public static void main(String[] args) {
        User user = new User();

//        PropertyValue propertyValue1 = new PropertyValue("id", 1L);
//        PropertyValue propertyValue2 = new PropertyValue("name", "wfr");
//        PropertyValue propertyValue3 = new PropertyValue("city", "WUHAN");
//        List<PropertyValue> pvs = new ArrayList<>(3);
//        pvs.add(propertyValue1);
//        pvs.add(propertyValue2);
//        pvs.add(propertyValue3);

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        map.put("name", "wangfarui");
        map.put("city", "WUHAN");
        map.put("age", 18);
        map.put("company.name", "china");

        PropertyValues propertyValues = new MutablePropertyValues(map);

        DataBinder dataBinder = new DataBinder(user);
        // 默认true
        // dataBinder.setIgnoreUnknownFields(false);
        // 默认true
        dataBinder.setAutoGrowNestedPaths(true);
        // 默认false
        dataBinder.setIgnoreInvalidFields(false);
        // 必填字段，不会抛异常，而是存在BindingResult中
        dataBinder.setRequiredFields("id", "name", "beanName");

        dataBinder.bind(propertyValues);

        BindingResult bindingResult = dataBinder.getBindingResult();

        System.out.println(bindingResult);
        System.out.println(user);

    }
}
