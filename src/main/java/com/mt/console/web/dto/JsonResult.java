package com.mt.console.web.dto;

import java.util.Collection;

import lombok.Data;

/**
 * <pre>
 *     Json格式
 * </pre>
 *
 * @author : maitao
 * @date : 2019/7/27
 */
@Data
public class JsonResult<T> {

    /**
     * 返回的状态码 (Same as HttpStatus.value()).
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * Dev message.(only setting in dev environment)
     */
    private String devMsg;

    /**
     * 返回的数据
     */
    private Object result;

    public JsonResult() {
    }

    /**
     * 只返回状态码
     *
     * @param code 状态码
     */
    public JsonResult(Integer code) {
        this.code = code;
    }

    /**
     * 不返回数据的构造方法
     *
     * @param code 状态码
     * @param msg  信息
     */
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回数据的构造方法
     *
     * @param code   状态码
     * @param msg    信息
     * @param result 数据
     */
    public JsonResult(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    /**
     * 返回状态码和数据
     *
     * @param code   状态码
     * @param result 数据
     */
    public JsonResult(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }

    public JsonResult(Integer code, String msg, String devMsg, Object result) {
        this.code = code;
        this.msg = msg;
        this.devMsg = devMsg;
        this.result = result;
    }
    
    public static JsonResult error(int code, String message) {
    	JsonResult resultBean = new JsonResult();
        resultBean.setCode(code);
        resultBean.setMsg(message);
        return resultBean;
    }

    public static JsonResult success() {
    	JsonResult resultBean = new JsonResult();
        resultBean.setCode(0);
        resultBean.setMsg("success");
        return resultBean;
    }

    public static <V> JsonResult<V> success(Collection<V> data) {
    	JsonResult resultBean = new JsonResult();
        resultBean.setCode(0);
        resultBean.setMsg("success");
        resultBean.setResult(data);
        return resultBean;
    }
}
