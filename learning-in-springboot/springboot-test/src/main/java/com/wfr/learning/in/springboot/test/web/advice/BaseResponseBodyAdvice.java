package com.wfr.learning.in.springboot.test.web.advice;

import com.wfr.learning.in.springboot.test.core.utils.MethodParameterUtils;
import com.wfr.learning.in.springboot.test.web.ApiStatus;
import com.wfr.learning.in.springboot.test.web.BaseResponse;
import com.wfr.learning.in.springboot.test.web.BaseResponseWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

/**
 * 基础响应对象包装器
 * <p>对应的请求对象处理器则为{@link RequestBodyAdvice}</p>
 *
 * @author wangfarui
 * @since 2022/6/23
 */
@ControllerAdvice
public class BaseResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().isAssignableFrom(BaseResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        BaseResponseWrapper baseResponseWrapper = MethodParameterUtils.obtainResponseWrapper(returnType, BaseResponseWrapper.class);
        if (baseResponseWrapper == null) {
            return BaseResponse.success(body);
        }

        if (!baseResponseWrapper.useWrapper()) {
            return body;
        }

        BaseResponse<Object> baseResponse = BaseResponse.success(body);
        if (baseResponseWrapper.code() != ApiStatus.DEFAULT_CODE) {
            baseResponse.setCode(baseResponseWrapper.code());
        }
        if (!ApiStatus.DEFAULT_MESSAGE.equals(baseResponseWrapper.message())) {
            baseResponse.setMessage(baseResponseWrapper.message());
        }

        return baseResponse;
    }
}
