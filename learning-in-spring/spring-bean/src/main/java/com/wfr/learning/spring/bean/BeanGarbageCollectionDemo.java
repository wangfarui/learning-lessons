package com.wfr.learning.spring.bean;

import com.wfr.learning.spring.bean.initialization.BeanInitializationDemo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring Bean 垃圾回收 示例
 *
 * @author wangfarui
 * @since 2022/1/19
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 BeanInitialization 类中的Bean
        applicationContext.register(BeanInitializationDemo.class);

        applicationContext.refresh();

        applicationContext.close();

        System.gc();

        Thread.sleep(5000);

    }
}
