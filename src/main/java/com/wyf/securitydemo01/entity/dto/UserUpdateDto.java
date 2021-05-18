package com.wyf.securitydemo01.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author luoyuting
 * @date 2021-05-18 23:54
 * @description
 */
@Data
@ApiModel("修改用户信息dto对象")
public class UserUpdateDto {

    private Integer id;

    private String username;

    private String userRealName;

    private char sex;

    private String address;
}
