package com.wyf.securitydemo01.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author xxoo
 */
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    private int code;

    @Getter
    private String msg;

    @Getter
    private T data;

    private R() {
    }

    private R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(T data) {
        this.code = TipsEnum.SUCCESS.getCode();
        this.msg = TipsEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public static R ok() {
        return new R<>(null);
    }

    public static <T> R<T> ok(T data) {
        if (data == null) {
            return new R(StrUtil.EMPTY);
        }
        return new R<>(data);
    }

    public static String okByJsonStr(String jsonStr) {
        return "{\"code\":200,\"msg\":\"操作成功\",\"data\":" + jsonStr + "}";
    }

    public String toJsonStr() {
        return JSONUtil.toJsonStr(this);
    }

    public static <T> R<T> build(Integer code, String message) {
        return new R<>(code, message);
    }

    public static <T> R<T> build(ITipsEnum tipsEnum) {
        return new R<>(tipsEnum.getCode(), tipsEnum.getMessage());
    }

    public static <T> R<T> build(ITipsEnum tipsEnum, T data) {
        return new R<>(tipsEnum.getCode(), tipsEnum.getMessage(), data);
    }

    public static <T> R<T> build(Throwable e) {
        return new R<>(TipsEnum.FAIL.getCode(), e.getMessage());
    }
}
