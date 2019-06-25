package com.neuedu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 封装高复用的Response对象
 * @param <T>
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServletResponse<T> {

    private ServletResponse()
    {

    }

    private ServletResponse(Integer status)
    {
        this.status = status;
    }
    private ServletResponse(Integer status,String msg)
    {
        this.status = status;
        this.msg = msg;
    }
    public ServletResponse(Integer status,T data) {
        this.status = status;
        this.data = data;
    }
    private ServletResponse(Integer status,T data,String msg)
    {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }


    /**
     * 判断是否请求成功
     */
    @JsonIgnore
    public boolean isReqSuccess()
    {
        return this.status == StatusCode.SUCCESS;
    }

    /**
     * 请求成功时,对外公开的方法
     */
    public static ServletResponse getRequestSuccess()
    {
        return new ServletResponse(StatusCode.SUCCESS);
    }
    public static ServletResponse getRequestSuccess(String msg)
    {
        return new ServletResponse(StatusCode.SUCCESS,msg);
    }
    public static <T> ServletResponse getRequestSuccess(T data)
    {
        return  new ServletResponse(StatusCode.SUCCESS,data);
    }
    public static <T> ServletResponse getRequestSuccess(String msg,T data)
    {
        return new ServletResponse(StatusCode.SUCCESS,data,msg);
    }



    /**
     * 判断是否请求成功
     */
    @JsonIgnore
    public boolean isReqFailed()
    {
        return this.status == StatusCode.FAILED;
    }
    /**
     * 请求失败时,对外公开的方法
     */
    public static ServletResponse getRequestFailed()
    {
        return new ServletResponse(StatusCode.FAILED);
    }
    public static ServletResponse getRequestFailed(String msg)
    {
        return new ServletResponse(StatusCode.FAILED,msg);
    }


    private Integer status;

    private T data;

    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
