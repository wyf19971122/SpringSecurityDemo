package com.wyf.securitydemo01.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author luoyuting
 * @date 2021-05-17 22:10
 * @description
 */
@ApiModel("新增用户实体类")
@Data
public class UserAddDto {

    @ApiModelProperty("用户名")
    @NotNull
    private String username;

    @ApiModelProperty("真实姓名")
    private String userRealName;

    @ApiModelProperty("性别")
    private char sex;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("居住地址")
    private String address;

}
