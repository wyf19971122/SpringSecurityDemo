package com.wyf.securitydemo01.util;

import lombok.Getter;

/**
 * 响应状态码
 */
@Getter
public enum TipsEnum implements ITipsEnum {

    /**
     * 业务接口响应相关
     */
    ERROR(999999, "系统异常，请稍后再试"),
    PARAM_INVALID(900001, "参数无效"),

    /**
     * 请求相关
     */
    SUCCESS(200, "操作成功"),
    RE_LOGIN(300, "登录超时,请重新登录"),
    FAIL(400, "系统繁忙, 请稍后再试"),
    REQUEST_PARAM_LACK(401, "缺少必要参数 "),
    REQUEST_PARAM_ERROR(402, "参数类型有误 "),
    INVALID_CAPTCHA(403, "验证码不正确"),
    ILLEGAL_PARAMETER(404, "非法参数"),
    PARAM_SIGN_ERROR(406, "参数签名有误"),
    USERNAME_IS_EXIST(407, "用户名已存在"),
    USERNAME_NOT_EXIST(407,"用户名不存在"),
    QPS(408, "当前请求数过高，请稍后再试"),
    AUTHORIZATION_FAIL(409, "授权失败,连接断开"),
    CLIENT_LOGIN_FALL(410,"客户端登陆失败"),
    GET_DEPT_FALL(411,"同步部门列表失败"),
    CLIENT_REGISTER_FALL(412,"客户端注册失败"),
    CAPTCHA_IS_EXPIRE(413,"验证码已过期"),
    CAPTCHA_ERROR(414,"验证码错误"),
    USER_PWD_ERROR(415,"用户名或密码错误"),
    USER_IS_NOT_AUTH(416,"该用户未激活，请前往注册邮箱激活"),
    NO_LOGIN(417,"用户未登录，请前往登录"),
    THRID_ERROR(418,"第三方返回异常"),
    SAFETY_ERROR(419,"安全管理错误"),
    PART_DATA_ERROR(420,"批量操作数据部分失败"),
    SESSION_ERROR(421,"登录已过期或异地登录，请重新登录!"),
    LAST_LOGIN(422,"您今日的登录次数已用完，后续将无法登录！"),
    BUSINESS_FAIL(423, "业务操作异常"),
    INVALID_FAIL(424, "第三方凭证appKey无效"),
    /**
     *  文件相关
     */
    FILE_FORMAT_ERROR(600,"文件格式有误"),
    FILE_MAX_SIZE(601,"文件最大尺寸{0}mb")

    ;

    TipsEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;


    public static int getCode(TipsEnum tm) {
        return tm.code;
    }


}
