package com.wyf.securitydemo01.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wyf
 * @Date: 2021/5/19 10:36
 */
@Data
@ApiModel("数据查询相关参数")
public class DataDto {

    @ApiModelProperty("数据标题")
    private String title;

    @ApiModelProperty("数据年限")
    private String year;

    @ApiModelProperty("页面大小")
    private Integer size;

    @ApiModelProperty("当前页面")
    private Integer current;
}
