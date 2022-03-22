package com.wfr.learning.dependency.source;

import com.wfr.learning.dependency.source.bean.MyUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

/**
 *{@link Value} 外部化配置 作为依赖来源示例
 *
 * @author wangfarui
 * @since 2022/3/10
 */
@Configuration
// 并非只能读取标准格式(properties、yaml、yml等)的资源文件, 文件后缀名可随意
@PropertySource(value = "classpath:/META-INF/my.properties", encoding = "GBK")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${my.id:1}")
    private Long myId;

    /**
     * 对应的Bean name = myUser 的 username 变量
     * 并且username变量需要是public或者有get方法
     */
    @Value("#{myUser.username?:wfr}")
    private String myName;

    @Value("${my.address:wuhan}")
    private String address2;

    @Value("${my.resource:classpath://my.properties}")
    private Resource resource;

    @PostConstruct
    public void init() {
        System.out.println(myId);
        System.out.println(myName);
        System.out.println(address2);
        System.out.println(resource);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        applicationContext.register(MyUser.class);
        applicationContext.refresh();


        applicationContext.close();
    }
}
