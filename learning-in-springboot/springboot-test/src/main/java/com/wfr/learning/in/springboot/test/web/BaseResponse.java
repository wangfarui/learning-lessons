package com.wfr.learning.in.springboot.test.web;

/**
 * 基础响应对象
 *
 * @author wangfarui
 * @since 2022/6/23
 */
public class BaseResponse<T> {

    private int code;

    private String message;

    private String traceId;

    private T data;

    public BaseResponse(ApiStatus apiStatus, T data) {
        this(apiStatus.getCode(), apiStatus.getMessage(), data);
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(BaseApiStatus.SUCCESS, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(BaseApiStatus.SUCCESS, data);
    }

    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<>(BaseApiStatus.SUCCESS.getCode(), message, null);
    }

    public static <T> BaseResponse<T> fail(ApiStatus apiStatus) {
        return new BaseResponse<>(apiStatus, null);
    }

    public static <T> BaseResponse<T> fail(int code, String message) {
        return new BaseResponse<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
