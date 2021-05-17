package com.wyf.securitydemo01.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author luoyuting
 * @date 2021-05-16 21:26
 * @description
 */
@Data
@ApiModel("用户查询相关参数")
public class UserDto {

    private String username;

    private Integer size;

    private Integer current;
}
