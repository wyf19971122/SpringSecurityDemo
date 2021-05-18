package com.wyf.securitydemo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.entity.dto.SysLogDto;
import com.wyf.securitydemo01.entity.pojo.OdSysLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wyf
 * @Date: 2021/5/18 13:40
 */
@Repository
public interface OdSysLogMapper extends BaseMapper<OdSysLog> {
    List<OdSysLog> listPage(Page<OdSysLog> page, @Param("dto") SysLogDto dto);
}
