package com.wfr.learning.in.springboot.test.web.starter;

import com.wfr.learning.in.springboot.test.dict.constrants.SpringBeanOrderConstants;
import com.wfr.learning.in.springboot.test.web.advice.BaseResponseBodyAdvice;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.Order;

/**
 * 响应对象处理器自动装配类
 *
 * @author wangfarui
 * @since 2022/6/24
 */
@Configuration(proxyBeanMethods = false)
@Role(BeanDefinition.ROLE_SUPPORT)
@Order(SpringBeanOrderConstants.WEB_RESPONSE_BODY_ADVICE)
public class BaseResponseBodyAdviceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BaseResponseBodyAdvice getBaseResponseBodyAdvice() {
        return new BaseResponseBodyAdvice();
    }
}
