package com.example.readwrite.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kai.tang
 * @version 1.0-SNAPSHOT
 * @date 2020/5/14 9:11
 */
@Data
public class BaseResponse<T> implements Serializable {
    /**
     * 0-正常 -1-异常
     */
    private int code;

    private String msg;

    private T data;

    public static <T> BaseResponse<T> success() {
        BaseResponse<T> baseResponse = new BaseResponse();
        baseResponse.setCode(ResultCodeConstant.SUCCESS.getCode());
        baseResponse.setMsg(ResultCodeConstant.SUCCESS.getMsg());
        return baseResponse;
    }

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResultCodeConstant.SUCCESS.getCode());
        baseResponse.setMsg(ResultCodeConstant.SUCCESS.getMsg());
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponse<T> error() {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResultCodeConstant.ERROR.getCode());
        return baseResponse;
    }

    public static <T> BaseResponse<T> param() {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResultCodeConstant.PARAM.getCode());
        baseResponse.setMsg(ResultCodeConstant.PARAM.getMsg());
        return baseResponse;
    }


    public static <T> BaseResponse<T> error(String msg) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResultCodeConstant.ERROR.getCode());
        baseResponse.setMsg(msg);
        return baseResponse;
    }

    public static <T> BaseResponse<T> error(int code, String msg) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        return baseResponse;
    }

    public static <T> BaseResponse<T> success(int code, String msg) {
        BaseResponse<T> baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        return baseResponse;
    }
    
    public static <T> BaseResponse<T> instance(int code, String msg, T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        baseResponse.setData(data);
        return baseResponse;
    }
}
