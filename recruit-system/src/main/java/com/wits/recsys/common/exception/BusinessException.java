package com.wits.recsys.common.exception;

import com.wits.recsys.common.result.Result;

/**
 * 自定义业务异常（用于业务逻辑错误）
 */
public class BusinessException extends RuntimeException {
    private Integer code;

    // 仅错误信息，默认业务异常码501
    public BusinessException(String msg) {
        super(msg);
        this.code = Result.BUSINESS_ERROR;
    }

    // 自定义状态码+错误信息
    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}