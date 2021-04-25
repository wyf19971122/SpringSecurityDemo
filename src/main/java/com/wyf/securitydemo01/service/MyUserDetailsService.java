package com.wyf.securitydemo01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyf.securitydemo01.entity.Users;
import com.wyf.securitydemo01.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wyf
 * @Date: 2021/4/21 14:23
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //调用usersMapper的方法根据用户名查询数据库
        QueryWrapper<Users> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        Users users = usersMapper.selectOne(queryWrapper);
        //判断
        if(null == users){//数据库没有用户名
            throw new UsernameNotFoundException("用户名不存在！");
        }

        //admins权限
//        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins");
        //sale权限
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_manager");
        return new User(users.getUsername(),new BCryptPasswordEncoder().encode(users.getPassword()),auths);
    }
}
