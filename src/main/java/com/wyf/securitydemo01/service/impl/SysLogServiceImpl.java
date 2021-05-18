package com.wyf.securitydemo01.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.entity.dto.SysLogDto;
import com.wyf.securitydemo01.entity.pojo.OdSysLog;
import com.wyf.securitydemo01.exceptionHandle.CustomerException;
import com.wyf.securitydemo01.mapper.OdSysLogMapper;
import com.wyf.securitydemo01.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wyf
 * @Date: 2021/5/18 15:09
 */
@Service
@Slf4j
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private OdSysLogMapper sysLogMapper;

    @Override
    public Page<OdSysLog> queryLogPage(SysLogDto dto) {
        Page<OdSysLog> page = new Page<>();
        page.setSize(dto.getSize());
        page.setCurrent(dto.getCurrent());
        List<OdSysLog> result = sysLogMapper.listPage(page, dto);
        page.setRecords(result);
        page.setTotal(result.size());
        return page;
    }

    @Override
    public void removeById(Integer id) {
        int i = sysLogMapper.deleteById(id);
        if (i < 1) {
            throw new CustomerException("删除日志失败");
        }
    }
}
