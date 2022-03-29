package com.wfr.learning.in.spring.bean.scope;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 基于 {@link ThreadLocalScope} 的自定义Scope作用域示例
 *
 * @author wangfarui
 * @since 2022/3/22
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(scopeName = ThreadLocalScope.THREAD_LOCAL_SCOPE_NAME)
    public User threadLocalUser() {
        User user = new User();
        user.setId(System.nanoTime());
        user.setName("thread local user");
        return user;
    }

    @Autowired
    public User user1;

    @Autowired
    public User user2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.THREAD_LOCAL_SCOPE_NAME, new ThreadLocalScope());
        });
        applicationContext.refresh();

        ThreadLocalScopeDemo demo = applicationContext.getBean(ThreadLocalScopeDemo.class);

        System.out.println("user1: " + demo.user1);
        System.out.println("user2: " + demo.user2);

        getUserBeanByDifferentThread(applicationContext);

        applicationContext.close();
    }

    private static void getUserBeanByDifferentThread(AnnotationConfigApplicationContext applicationContext) {
        ThreadLocalScopeDemo demo = applicationContext.getBean(ThreadLocalScopeDemo.class);

        int n = 5;
        while (n-- > 0) {
            Thread thread = new Thread(() -> {
                User userByThread = applicationContext.getBean(User.class);
                System.out.println(Thread.currentThread().getName() + " - " + userByThread);
            });
            // 启动线程
            thread.start();
            // 强制线程执行完成, 即同步完成(顺序执行)
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
