package com.wyf.securitydemo01.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.aop.SysLog;
import com.wyf.securitydemo01.entity.dto.DataAddDto;
import com.wyf.securitydemo01.entity.dto.DataDto;
import com.wyf.securitydemo01.entity.dto.DataUpdateDto;
import com.wyf.securitydemo01.entity.pojo.ComsumeData;
import com.wyf.securitydemo01.entity.pojo.GrossIndustrialWageData;
import com.wyf.securitydemo01.service.GrossIndustrialWageDataService;
import com.wyf.securitydemo01.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wyf
 * @Date: 2021/5/19 10:05
 */
@Slf4j
@RestController
@RequestMapping("/grossIndustrialWageData")
@AllArgsConstructor
@CrossOrigin
@Api(tags = "产业信息相关接口",value = "产业信息相关接口")
public class GrossIndustrialWageDataController {
    @Autowired
    private GrossIndustrialWageDataService grossIndustrialWageDataService;
    //分页查询
    @PostMapping("/queryWageDataPage")
    @ApiOperation("分页查询日志信息")
    public R queryCDataPage(@Validated @RequestBody DataDto dto){
        Page<GrossIndustrialWageData> result = grossIndustrialWageDataService.queryDWDataPage(dto);
        return R.ok(result);
    }

    @GetMapping("/removeById")
    @ApiOperation("删除产业信息")
    @SysLog(value = "删除产业信息")
    public R removeById(@RequestParam Integer id){
        grossIndustrialWageDataService.removeById(id);
        return R.ok("删除产业信息成功！");
    }

    @GetMapping("/queryById")
    @ApiOperation("根据id查产业信息")
    @SysLog(value = "查询产业信息")
    public R queryById(@RequestParam Integer id){
        GrossIndustrialWageData grossIndustrialWageData = grossIndustrialWageDataService.queryById(id);
        return R.ok(grossIndustrialWageData);
    }
    @PostMapping("/add")
    @ApiOperation("添加产业信息")
    @SysLog(value = "添加产业信息")
    public R addData(@Validated @RequestBody DataAddDto dto){
        grossIndustrialWageDataService.add(dto);
        return R.ok("添加产业信息成功！");
    }
    @PostMapping("/update")
    @ApiOperation("修改产业信息")
    @SysLog(value = "修改产业信息")
    public R addData(@Validated @RequestBody DataUpdateDto dto){
        grossIndustrialWageDataService.update(dto);
        return R.ok("修改产业信息成功！");
    }

}
