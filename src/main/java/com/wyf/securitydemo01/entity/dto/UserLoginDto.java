package com.wyf.securitydemo01.entity.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wyf
 * @Date: 2021/5/14 13:30
 */
@Data
@ApiModel("用户登录实体类")
public class UserLoginDto {
    @ApiModelProperty(value = "用户登录名",required = true)
    @NotNull(message = "请输入用户名")
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NotNull(message = "请输入密码")
    private String password;

    @ApiModelProperty("验证码")
    @NotNull(message = "缺少必要参数")
    private String code;

    @ApiModelProperty("验证码key")
    @NotNull(message = "请输入验证码")
    private String randomStr;

}
