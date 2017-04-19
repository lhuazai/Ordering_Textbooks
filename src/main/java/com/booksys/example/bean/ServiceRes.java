package com.booksys.example.bean;

/**
 * Created by Administrator on 2017/4/10.
 */
public class ServiceRes<T> {
    private T t;
    private String message;
    private Boolean result;

    public ServiceRes() {
        super();
    }

    public ServiceRes(T t, Boolean result, String message) {
        this.t = t;
        this.message = message;
        this.result = result;
    }

    public ServiceRes(String message, Boolean result) {
        this.message = message;
        this.result = result;
    }

    public ServiceRes(T t) {
        this.t = t;
        this.result=true;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
