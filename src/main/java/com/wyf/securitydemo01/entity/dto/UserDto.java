package com.wyf.securitydemo01.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author luoyuting
 * @date 2021-05-16 21:26
 * @description
 */
@Data
@ApiModel("用户查询相关参数")
public class UserDto {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("页面大小")
    private Integer size;

    @ApiModelProperty("当前页面")
    private Integer current;
}
