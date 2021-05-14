package com.wyf.securitydemo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.wyf.securitydemo01.entity.dto.UserLoginDto;
import com.wyf.securitydemo01.entity.pojo.Users;
import com.wyf.securitydemo01.exceptionHandle.LoginException;
import com.wyf.securitydemo01.mapper.UsersMapper;
import com.wyf.securitydemo01.service.UserService;
import com.wyf.securitydemo01.util.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wyf
 * @Date: 2021/5/14 15:56
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public R login(UserLoginDto dto){
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(dto.getUsername());
        if (null == userDetails){
            throw new LoginException("用户名或密码错误！");
        }
        HashMap<String, Object> result = Maps.newHashMap();
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",dto.getUsername());
        Users users = usersMapper.selectOne(queryWrapper);
        if (!users.getPassword().equals(dto.getPassword())){
            throw new LoginException("用户名或密码错误！");
        }
        result.put("id", users.getId());
        result.put("username", dto.getUsername());
        return R.ok(result);
    }
}
