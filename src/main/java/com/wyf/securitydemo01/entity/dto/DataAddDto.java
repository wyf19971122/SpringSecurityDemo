package com.wyf.securitydemo01.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: wyf
 * @Date: 2021/5/19 10:09
 */
@Data
@ApiModel("数据添加dto类")
public class DataAddDto {

    private String year;

    private String title;

    private String number;

    private Integer userId;
}
