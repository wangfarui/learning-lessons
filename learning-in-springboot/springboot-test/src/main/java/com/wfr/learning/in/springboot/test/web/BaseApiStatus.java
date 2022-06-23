package com.wfr.learning.in.springboot.test.web;

/**
 * API响应对象的基础HTTP状态
 *
 * @author wangfarui
 * @see ApiStatus
 * @since 2022/6/23
 */
public enum BaseApiStatus implements ApiStatus {

    SUCCESS(200, "Success"),
    CLIENT_ERROR(400, "Client Error"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    SERVER_ERROR(500, "Server Error"),
    BAD_GATEWAY(502, "Bad Gateway"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    ;


    private final int code;

    private final String message;

    BaseApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
