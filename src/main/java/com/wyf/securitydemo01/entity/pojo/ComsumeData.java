package com.wyf.securitydemo01.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wyf
 * @Date: 2021/5/19 9:52
 */
@TableName("comsume_data")
@Data
@ApiModel("消费信息管理实体类")
public class ComsumeData {

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String year;

    private String title;

    private String number;

    private String createUser;

    private String createTime;
}
