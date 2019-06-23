package com.yuandian.core.common;

public class ResultObject<T> {
    private short code;
    private T t;

    public ResultObject(short code, T t) {
        this.code = code;
        this.t = t;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public boolean success() {
        return code == ErrorCode.SYS_SUCCESS;
    }
}
