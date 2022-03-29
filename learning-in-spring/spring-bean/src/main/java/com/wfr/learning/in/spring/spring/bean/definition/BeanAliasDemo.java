package com.wfr.learning.in.spring.spring.bean.definition;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * {@link org.springframework.core.AliasRegistry} bean Alias demo
 *
 * @author wangfarui
 * @since 2022/1/17
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        String path = "classpath:/META-INF/bean-definition-context.xml";
        // 创建 BeanFactory 容器, 并启动Spring上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);

        String[] aliases = applicationContext.getAliases("wangfarui-user");
        System.out.println(Arrays.toString(aliases));

        User user2 = applicationContext.getBean("user2", User.class);
        User aliasUser = applicationContext.getBean("wangfarui-user", User.class);

        System.out.println(user2);
        System.out.println(aliasUser);

        System.out.println("user2 与 aliasUser 是否相同: " + (user2 == aliasUser));

    }
}
