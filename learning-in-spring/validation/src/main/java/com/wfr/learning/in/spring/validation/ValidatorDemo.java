package com.wfr.learning.in.spring.validation;

import com.wfr.learning.in.spring.ioc.container.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.Locale;

/**
 * 自定义 {@link org.springframework.validation.Validator} 校验示例
 *
 * @author wangfarui
 * @since 2022/3/30
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        // 1. 创建 Validator
        Validator validator = new UserValidator();
        // 2. 判断是否支持目标对象的类型
        User user = new User();
        System.out.println("user 对象是否被 UserValidator 支持检验：" + validator.supports(user.getClass()));
        // 3. 创建 Errors 对象
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        // 4. 获取 MessageSource 对象
        MessageSource messageSource = ErrorsMessageDemo.createMessageSource();

        // 5. 输出所有的错误文案
        for (ObjectError error : errors.getAllErrors()) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
            String userName = user.getName();
            // ...
        }
    }
}
