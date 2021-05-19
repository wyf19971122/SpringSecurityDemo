package com.wyf.securitydemo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.wyf.securitydemo01.entity.dto.*;
import com.wyf.securitydemo01.entity.pojo.Users;
import com.wyf.securitydemo01.exceptionHandle.CustomerException;
import com.wyf.securitydemo01.exceptionHandle.LoginException;
import com.wyf.securitydemo01.mapper.UsersMapper;
import com.wyf.securitydemo01.service.UserService;
import com.wyf.securitydemo01.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
        result.put("userRealName", users.getUserRealName());
        return R.ok(result);
    }

    @Override
    public Page<Users> queryUserPage(UserDto dto) {
        Page<Users> page = new Page<>();
        page.setSize(dto.getSize());
        page.setCurrent(dto.getCurrent());
        List<Users> result = usersMapper.listPage(page, dto);
        page.setRecords(result);
        page.setTotal(result.size());
        return page;

    }

    @Override
    public Users queryById(Integer id) {
        Users users = usersMapper.selectById(id);
        if (null == users){
            throw new CustomerException("没有该用户！");
        }
        return users;
    }

    @Override
    public void removeUser(Integer id) {
        int i = usersMapper.deleteById(id);
        if (i < 1){
            throw new CustomerException("删除用户失败");
        }
    }

    @Override
    public void updatePassword(UpdatePasswordDto dto) {
        Users users = usersMapper.selectById(dto.getId());
        if (!dto.getOldPwd().equals(users.getPassword())){
            throw new CustomerException("修改密码失败,原密码错误");
        }
        if (!dto.getNewPwd().equals(dto.getConfirmPwd())){
            throw new CustomerException("修改密码不一致，两次密码不一致");
        }
        users.setPassword(dto.getConfirmPwd());
        usersMapper.updateById(users);
    }

    @Override
    public void addUser(UserAddDto dto) {
        if (null == dto){
            throw new CustomerException("添加用户失败！");
        }
        Users users = new Users();
        BeanUtils.copyProperties(dto,users);
        users.setPassword("123");
        int rows = usersMapper.insert(users);
        if (rows < 1){
            throw new CustomerException("添加用户失败！");
        }
    }

    @Override
    public void updateUser(UserUpdateDto dto) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_real_name",dto.getUserRealName());
        queryWrapper.ne("id",dto.getId());
        List<Users> users = usersMapper.selectList(queryWrapper);
        if (users.size() > 0){
            throw new CustomerException("修改失败，真实姓名不能一样！");
        }
        Users users1 = usersMapper.selectById(dto.getId());
        BeanUtils.copyProperties(dto,users1);
        int i = usersMapper.updateById(users1);
        if (i < 1){
            throw new CustomerException("修改信息失败！");
        }
    }
}
