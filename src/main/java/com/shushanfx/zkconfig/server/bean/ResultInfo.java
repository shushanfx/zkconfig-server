package com.shushanfx.zkconfig.server.bean;

/**
 * Created by shushanfx on 17/六月/11.
 */
public class ResultInfo<T extends Object> {
    public static final int CODE_SUCCESS = 0x01;
    public static final int CODE_FAILURE = -0x01;
    public static final String MESSAGE_SUCCESS = "success";
    public static final String MESSAGE_FAILURE = "fail";


    private int code;
    private String message = null;
    private T data = null;

    public ResultInfo(int code, String message) {
        this.code = code;
        this.message = message;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultInfo newSuccess(){
        return new ResultInfo(CODE_SUCCESS, MESSAGE_SUCCESS);
    }

    public static ResultInfo newFailure(){
        return new ResultInfo(CODE_FAILURE, MESSAGE_FAILURE);
    }
}
