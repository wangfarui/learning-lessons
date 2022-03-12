package com.wfr.learning.ioc.container.overview.dependency.lookup;

import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 基于 {@link FactoryBean} 的依赖查找
 *
 * @author wangfarui
 * @since 2022/3/12
 */
public class DependencyLookupToFactoryBeanDemo {

    public static void main(String[] args) throws Exception {
        String location = "classpath:/META-INF/dependency-lookup-factory-bean.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(location);

        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);

        for (Map.Entry<String, User> entry : beansOfType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("==============================================");

        // 注册的 FactoryBean , 通过Class类型查找时，是以 FactoryBean#getObjectType 方法返回的Class作为查找类型
        User bean = applicationContext.getBean("userFactory2", User.class);
        System.out.println("userFactory2: " + bean);


        System.out.println("==============================================");

        Map<String, FactoryBean> factoryBeanMap = applicationContext.getBeansOfType(FactoryBean.class);
        for (Map.Entry<String, FactoryBean> entry : factoryBeanMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getObject());
        }

        applicationContext.close();

    }
}
