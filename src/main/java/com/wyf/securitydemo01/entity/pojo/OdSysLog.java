package com.wyf.securitydemo01.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * @Author: wyf
 * @Date: 2021/5/18 11:47
 */
@Data
@TableName("od_sys_log")
public class OdSysLog {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("ip地址")
    private String ipAddr;

    @ApiModelProperty("日志标题")
    private String title;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

}
