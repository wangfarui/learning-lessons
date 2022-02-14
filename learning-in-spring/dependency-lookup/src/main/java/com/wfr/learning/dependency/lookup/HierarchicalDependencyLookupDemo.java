package com.wfr.learning.dependency.lookup;

import com.wfr.learning.ioc.container.overview.domain.SuperUser;
import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * 层级性依赖查找
 * {@link org.springframework.beans.factory.HierarchicalBeanFactory}
 *
 * @author wangfarui
 * @since 2022/2/14
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CollectionDependencyLookupDemo.class);
        applicationContext.refresh();

        // 根据Bean名称查找Bean是否存在
        boolean getSuperUser = applicationContext.containsLocalBean("getSuperUser");
        System.out.println("getSuperUser是否存在: " + getSuperUser);

        // 查看双亲BeanFactory
        System.out.println(applicationContext);
        BeanFactory beanFactory = applicationContext.getParentBeanFactory();
        System.out.println(beanFactory);

        // 根据Bean类型查找实例列表
        User user = BeanFactoryUtils.beanOfType(applicationContext, SuperUser.class);
        System.out.println(user);
        String[] strings = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, User.class);
        System.out.println(Arrays.toString(strings));

        applicationContext.close();
    }
}
