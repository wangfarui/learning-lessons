package com.wfr.learning.in.spring.dependency.injection;

import com.wfr.learning.in.spring.dependency.injection.annotation.UserGroup;
import com.wfr.learning.in.spring.ioc.container.overview.domain.SpecialUser;
import com.wfr.learning.in.spring.ioc.container.overview.domain.SuperUser;
import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 基于 {@link Qualifier} 注解依赖注入示例
 * <br/>
 * 并同时测试 {@link CollectionTypeDependencyInjectionDemo} 的集合类型注入示例,
 * 校验在有无 @Qualifier 注解的不同情况下的集合Bean的数据情况,
 * 自定义注解, 例如 {@link UserGroup}
 *
 * @author wangfarui
 * @since 2022/2/28
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Qualifier("myUser")
    @Autowired
    private User myUser;

    @UserGroup("mySuperUser")
    @Autowired
    private User mySuperUser;

    @Resource(type = SpecialUser.class)
    private User specialUser;

    @Autowired
    private Collection<User> userCollection;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUserCollection;

    @Autowired
    @UserGroup
    private Collection<User> userGroupUserCollection;

    @Bean("myUser")
    @Qualifier
    public User innerUser() {
        User user = User.createUser();
        user.setName("myUser");
        return user;
    }

    @Bean("mySuperUser")
    public SuperUser innerSuperUser() {
        SuperUser superUser = new SuperUser();
        superUser.setId(9L);
        superUser.setName("内建super用户");
        return superUser;
    }

    @Bean
    @UserGroup
    public SpecialUser innerSpecialUser() {
        SpecialUser specialUser = new SpecialUser();
        specialUser.setId(10L);
        specialUser.setMsg("特殊用户");
        return specialUser;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println("demo.user : " + demo.myUser);
        System.out.println("demo.mySuperUser : " + demo.mySuperUser);
        System.out.println("demo.specialUser : " + demo.specialUser);
        System.out.println("demo.userCollection : " + demo.userCollection.size() + " , " + demo.userCollection);
        System.out.println("demo.qualifierUserCollection : " + demo.qualifierUserCollection.size() + " , " + demo.qualifierUserCollection);
        System.out.println("demo.userGroupUserCollection : " + demo.userGroupUserCollection.size() + " , " + demo.userGroupUserCollection);

        applicationContext.close();
    }
}
