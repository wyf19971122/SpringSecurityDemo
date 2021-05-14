package com.wyf.securitydemo01.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wyf
 * @Date: 2021/4/21 14:49
 */
@Data
@TableName("users")
public class Users {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String userRealName;

    @ApiModelProperty(value = "性别")
    private char sex;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String address;
}
