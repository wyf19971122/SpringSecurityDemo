package com.wyf.securitydemo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.entity.dto.UserDto;
import com.wyf.securitydemo01.entity.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wyf
 * @Date: 2021/4/21 15:06
 */
@Repository
public interface UsersMapper extends BaseMapper<Users> {
    List<Users> selectUserByPage(Page<Users> page, @Param("dto") UserDto dto);
}
