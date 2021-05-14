package com.wyf.securitydemo01.service;

import com.wyf.securitydemo01.entity.dto.UserLoginDto;
import com.wyf.securitydemo01.util.R;

import java.util.Map;

/**
 * @Author: wyf
 * @Date: 2021/5/14 15:56
 */
public interface UserService {
    R login(UserLoginDto dto);
}
