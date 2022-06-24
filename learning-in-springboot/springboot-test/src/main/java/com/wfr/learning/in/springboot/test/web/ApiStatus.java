package com.wfr.learning.in.springboot.test.web;

/**
 * API响应对象的HTTP状态
 *
 * @author wangfarui
 * @see BaseResponse
 * @see org.springframework.http.HttpStatus
 * @since 2022/6/23
 */
public interface ApiStatus {

    /**
     * 默认HTTP状态码
     */
    int DEFAULT_CODE = 200;

    /**
     * 默认响应描述内容
     */
    String DEFAULT_MESSAGE = "Success";

    int getCode();

    String getMessage();
}
