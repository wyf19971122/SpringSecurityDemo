package com.wyf.securitydemo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyf.securitydemo01.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: wyf
 * @Date: 2021/4/21 15:06
 */
@Repository
public interface UsersMapper extends BaseMapper<Users> {
}
