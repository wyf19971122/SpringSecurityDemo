package com.wyf.securitydemo01.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author luoyuting
 * @date 2021-05-17 21:03
 * @description
 */
@Data
@ApiModel("密码修改实体类")
public class UpdatePasswordDto {

    //用户id
    @NotNull
    private Integer id;

    //原密码
    @NotNull
    private String oldPwd;

    //新密码
    @NotNull
    private String newPwd;

    //确认密码
    @NotNull
    private String confirmPwd;
}
