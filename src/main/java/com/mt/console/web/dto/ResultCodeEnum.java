package com.mt.console.web.dto;

/**
 * <pre>
 *     返回结果enum
 * </pre>
 *
 */
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(1),

    /**
     * 失败
     */
    FAIL(0);

    Integer code;

    ResultCodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
