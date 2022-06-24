package com.wfr.learning.in.springboot.test.dict.constrants;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Spring Bean 的排序常量
 *
 * @author wangfarui
 * @since 2022/6/24
 */
public abstract class SpringBeanOrderConstants implements Ordered {

    /**
     * 中等优先级
     *
     * @see Ordered#LOWEST_PRECEDENCE
     * @see Ordered#HIGHEST_PRECEDENCE
     */
    public static final int MIDDLE_PRECEDENCE = 0;

    /**
     * spring web中{@link RequestBodyAdvice}的优先级
     */
    public static final int WEB_REQUEST_BODY_ADVICE = LOWEST_PRECEDENCE - 3;

    /**
     * spring web中{@link ExceptionHandler}的优先级
     */
    public static final int WEB_EXCEPTION_BODY_ADVICE = LOWEST_PRECEDENCE - 2;

    /**
     * spring web中{@link ResponseBodyAdvice}的优先级
     */
    public static final int WEB_RESPONSE_BODY_ADVICE = LOWEST_PRECEDENCE - 1;

}
