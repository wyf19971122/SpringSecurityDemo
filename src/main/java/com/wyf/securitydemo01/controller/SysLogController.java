package com.wyf.securitydemo01.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.aop.SysLog;
import com.wyf.securitydemo01.entity.dto.SysLogDto;
import com.wyf.securitydemo01.entity.pojo.OdSysLog;
import com.wyf.securitydemo01.service.SysLogService;
import com.wyf.securitydemo01.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wyf
 * @Date: 2021/5/18 14:11
 */
@Slf4j
@RestController
@RequestMapping("/sysLog")
@AllArgsConstructor
@CrossOrigin
@Api(tags = "系统日志相关接口",value = "系统日志相关接口")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;
    //分页查询
    @PostMapping("/querySysLogPage")
    @ApiOperation("分页查询日志信息")
    public R queryLogPage(@Validated @RequestBody SysLogDto dto){
        Page<OdSysLog> result = sysLogService.queryLogPage(dto);
        return R.ok(result);
    }

    @GetMapping("/removeById")
    @SysLog(value = "删除日志")
    @ApiOperation("根据id删除日志")
    public R removeById(@RequestParam Integer id){
        sysLogService.removeById(id);
        return R.ok();
    }
}
