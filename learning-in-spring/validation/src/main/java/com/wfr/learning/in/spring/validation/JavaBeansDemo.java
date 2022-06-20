package com.wfr.learning.in.spring.validation;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * 关于 JavaBeans 的示例
 *
 * @see BeanInfo
 * @author wangfarui
 * @since 2022/6/20
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class, Introspector.USE_ALL_BEANINFO);

        String beanDescriptorName = beanInfo.getBeanDescriptor().getName();
        System.out.println("beanDescriptorName: " + beanDescriptorName);

        System.out.println("---propertyDescriptor---");
        System.out.printf("propertyDescriptor数量: %d个\n", beanInfo.getPropertyDescriptors().length);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(System.out::println);

        System.out.println("---methodDescriptor---");
        System.out.printf("methodDescriptor数量: %d个\n", beanInfo.getMethodDescriptors().length);
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }
}
