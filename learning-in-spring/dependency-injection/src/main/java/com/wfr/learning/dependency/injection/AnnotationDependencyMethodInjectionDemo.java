package com.wfr.learning.dependency.injection;

import com.wfr.learning.dependency.injection.bean.AnnotationInjectionBeans;
import com.wfr.learning.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解的依赖 方法 注入示例
 *
 * @author wangfarui
 * @since 2022/2/28
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    private UserHolder userHolder3;

    @Autowired
    public void customInit(User user) {
        this.userHolder = new UserHolder(user);
    }

    @Autowired
    public void customInit2(@Qualifier("customUserHolder") UserHolder userHolder) {
        this.userHolder2 = userHolder;
    }

    /**
     * 不指定 {@link Qualifier} 注解时，入参Bean的参数名可以直接取唯一Bean名称
     */
    @Resource
    public void customInit3(
            // @Qualifier("annotationUserHolder")
            UserHolder annotationUserHolder
    ) {
        this.userHolder3 = annotationUserHolder;
    }

    @Bean
    public UserHolder customUserHolder() {
        return new UserHolder(User.createUser());
    }

    /**
     * {@link Bean} 的Bean名称默认是方法名
     */
    @Bean
    public UserHolder annotationUserHolder(User user) {
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);
        applicationContext.register(AnnotationInjectionBeans.class);
        applicationContext.refresh();

        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
        System.out.println(demo.userHolder);
        System.out.println(demo.userHolder2);
        System.out.println(demo.userHolder3);

        System.out.println("userHolder == userHolder2 : " + demo.userHolder.equals(demo.userHolder2));
        System.out.println("userHolder == userHolder3 : " + demo.userHolder.equals(demo.userHolder3));

        applicationContext.close();
    }
}
