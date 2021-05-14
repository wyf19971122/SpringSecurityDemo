package com.wyf.securitydemo01.exceptionHandle;

/**
 * @Author: wyf
 * @Date: 2021/1/18 10:54
 */
public class LoginException extends RuntimeException{

    private int code;

    public LoginException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public LoginException() {
        super();
    }

    public LoginException(String msg) {
        super(msg);
    }

    public LoginException(Throwable e) {
        super(e);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return code;
    }
}
