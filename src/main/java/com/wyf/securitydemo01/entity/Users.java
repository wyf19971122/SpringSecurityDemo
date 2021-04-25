package com.wyf.securitydemo01.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: wyf
 * @Date: 2021/4/21 14:49
 */
@Data
@TableName("users")
public class Users {

    private Integer id;

    private String username;

    private String password;
}
