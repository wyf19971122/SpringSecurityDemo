package com.wyf.securitydemo01.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wyf
 * @Date: 2021/5/19 9:54
 */
@TableName("gross_industrial_wage_date")
@Data
@ApiModel("产业信息实体类")
public class GrossIndustrialWageData {

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String year;

    private String title;

    private String number;

    private String createUser;

    private String createTime;
}
