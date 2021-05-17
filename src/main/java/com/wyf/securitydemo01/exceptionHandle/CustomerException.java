package com.wyf.securitydemo01.exceptionHandle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luoyuting
 * @date 2020-08-19 0:17
 * @description
 */
@Setter
@Getter
public class CustomerException extends RuntimeException {
    protected String msg;
    protected Integer code;

    public CustomerException(String msg) {
        this.msg = msg;
    }
    public CustomerException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
