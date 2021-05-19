package com.wyf.securitydemo01.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: wyf
 * @Date: 2021/5/19 10:00
 */
@Data
@ApiModel("数据修改dto类")
public class DataUpdateDto {

    private Integer id;

    private String year;

    private String title;

    private String number;
}
