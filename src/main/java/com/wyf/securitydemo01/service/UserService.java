package com.wyf.securitydemo01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.entity.dto.UpdatePasswordDto;
import com.wyf.securitydemo01.entity.dto.UserAddDto;
import com.wyf.securitydemo01.entity.dto.UserDto;
import com.wyf.securitydemo01.entity.dto.UserLoginDto;
import com.wyf.securitydemo01.entity.pojo.Users;
import com.wyf.securitydemo01.util.R;

import java.util.List;
import java.util.Map;

/**
 * @Author: wyf
 * @Date: 2021/5/14 15:56
 */
public interface UserService {
    R login(UserLoginDto dto);

    List<Users> queryUserPage(UserDto dto);

    Users queryById(Integer id);

    void removeUser(Integer id);

    void updatePassword(UpdatePasswordDto dto);

    void addUser(UserAddDto dto);
}
