package com.wfr.learning.in.spring.validation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * Spring Bean Validation 示例
 *
 * @author wangfarui
 * @since 2022/6/20
 */
public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        String location = "classpath:/META-INF/bean-validation-context.xml";
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(location);

        UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);

        User user = new User();
//        user.setName("wfr");
        userProcessor.processName(user);

        applicationContext.close();
    }

    @Component
    @Validated
    static class UserProcessor {

        public void processName(@Valid User user) {
            System.out.println(user.toString());
        }
    }

    static class User {

        @NotBlank(message = "name不能为空")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


}
