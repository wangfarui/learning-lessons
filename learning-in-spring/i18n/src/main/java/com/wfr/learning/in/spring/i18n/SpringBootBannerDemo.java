package com.wfr.learning.in.spring.i18n;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 定制Spring Boot程序的Banner
 *
 * @author wangfarui
 * @see org.springframework.boot.Banner
 * @since 2022/6/16
 */
public class SpringBootBannerDemo {

    /**
     * 在{@link SpringApplication#run(String...)}方法的 printBanner 方法下输出banner.
     * 输出可以在Log日志中, 也可以在Console控制台中, 参考{@link org.springframework.boot.Banner.Mode}
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootBannerDemo.class);
        applicationContext.close();
    }
}
