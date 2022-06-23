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

    int getCode();

    String getMessage();
}
