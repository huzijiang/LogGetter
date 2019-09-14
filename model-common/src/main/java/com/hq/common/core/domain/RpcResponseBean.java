package com.hq.common.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RpcResponseBean<T> implements Serializable {
    private static final String SUCCESS = "0";
    //private static final int ERROR = -1;

    private String code;
    private String message;
    private T data;

    public RpcResponseBean() {
        this.code = SUCCESS;
        this.message = "success";
    }

    public RpcResponseBean(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RpcResponseBean(T object) {
        this.code = SUCCESS;
        this.message = "success";
        this.data = object;
    }

}
