package com.wfr.learning.in.spring.conversion;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 自定义 {@link java.beans.PropertyEditor} 示例
 *
 * @author wangfarui
 * @since 2022/6/28
 */
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        String path = "META-INF/property-editors-context.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);

        User xmlUser = applicationContext.getBean("xmlUser", User.class);

        System.out.println(xmlUser);

        applicationContext.close();
    }
}
