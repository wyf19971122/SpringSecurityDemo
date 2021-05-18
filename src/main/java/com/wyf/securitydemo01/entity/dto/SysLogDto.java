package com.wyf.securitydemo01.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wyf
 * @Date: 2021/5/18 14:19
 */
@Data
@ApiModel("系统日志查询相关参数")
public class SysLogDto {

    @ApiModelProperty("日志标题")
    private String title;

    @ApiModelProperty("页面大小")
    private Integer size;

    @ApiModelProperty("当前页面")
    private Integer current;
}
