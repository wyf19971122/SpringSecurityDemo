package com.wyf.securitydemo01.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.aop.SysLog;
import com.wyf.securitydemo01.entity.dto.DataAddDto;
import com.wyf.securitydemo01.entity.dto.DataDto;
import com.wyf.securitydemo01.entity.dto.DataUpdateDto;
import com.wyf.securitydemo01.entity.pojo.ComsumeData;
import com.wyf.securitydemo01.service.ComsumeDataService;
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
 * @Date: 2021/5/19 10:04
 */
@Slf4j
@RestController
@RequestMapping("/comsumeData")
@AllArgsConstructor
@CrossOrigin
@Api(tags = "消费信息相关接口",value = "消费信息相关接口")
public class ComsumeDataController {

    @Autowired
    private ComsumeDataService comsumeDataService;
    //分页查询
    @PostMapping("/queryComsumeDataPage")
    @ApiOperation("分页查询消费信息")
    public R queryCDataPage(@Validated @RequestBody DataDto dto){
        Page<ComsumeData> result = comsumeDataService.queryCDataPage(dto);
        return R.ok(result);
    }

    @GetMapping("/removeById")
    @ApiOperation("删除消费信息")
    @SysLog(value = "删除消息信息")
    public R removeById(@RequestParam Integer id){
        comsumeDataService.removeById(id);
        return R.ok("删除消费信息成功！");
    }

    @GetMapping("/queryById")
    @ApiOperation("根据id查询消息信息")
    @SysLog(value = "查询消费信息")
    public R queryById(@RequestParam Integer id){
        ComsumeData comsumeData = comsumeDataService.queryById(id);
        return R.ok(comsumeData);
    }
    @PostMapping("/add")
    @ApiOperation("添加消费信息")
    @SysLog(value = "添加消息信息")
    public R addData(@Validated @RequestBody DataAddDto dto){
        comsumeDataService.add(dto);
        return R.ok("添加消费信息成功！");
    }
    @PostMapping("/update")
    @ApiOperation("修改消费信息")
    @SysLog(value = "修改消息信息")
    public R addData(@Validated @RequestBody DataUpdateDto dto){
        comsumeDataService.update(dto);
        return R.ok("修改消费信息成功！");
    }



}
