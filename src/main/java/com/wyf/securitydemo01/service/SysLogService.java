package com.wyf.securitydemo01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.entity.dto.SysLogDto;
import com.wyf.securitydemo01.entity.pojo.OdSysLog;

import java.util.List;

/**
 * @Author: wyf
 * @Date: 2021/5/18 15:08
 */
public interface SysLogService {
    Page<OdSysLog> queryLogPage(SysLogDto dto);

    void removeById(Integer id);
}
