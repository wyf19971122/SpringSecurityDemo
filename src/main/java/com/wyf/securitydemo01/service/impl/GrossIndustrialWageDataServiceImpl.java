package com.wyf.securitydemo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.entity.dto.DataAddDto;
import com.wyf.securitydemo01.entity.dto.DataDto;
import com.wyf.securitydemo01.entity.dto.DataUpdateDto;
import com.wyf.securitydemo01.entity.pojo.ComsumeData;
import com.wyf.securitydemo01.entity.pojo.GrossIndustrialWageData;
import com.wyf.securitydemo01.entity.pojo.OdSysLog;
import com.wyf.securitydemo01.entity.pojo.Users;
import com.wyf.securitydemo01.exceptionHandle.CustomerException;
import com.wyf.securitydemo01.mapper.GrossIndustrialWageDataMapper;
import com.wyf.securitydemo01.mapper.UsersMapper;
import com.wyf.securitydemo01.service.GrossIndustrialWageDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: wyf
 * @Date: 2021/5/19 10:20
 */
@Service
@Slf4j
public class GrossIndustrialWageDataServiceImpl implements GrossIndustrialWageDataService {

    @Autowired
    private GrossIndustrialWageDataMapper grossIndustrialWageDataMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Page<GrossIndustrialWageData> queryDWDataPage(DataDto dto) {
        Page<GrossIndustrialWageData> page = new Page<>();
        page.setSize(dto.getSize());
        page.setCurrent(dto.getCurrent());
        if ((!StringUtils.isEmpty(dto.getTitle())&&(!StringUtils.isEmpty(dto.getYear())))){
            QueryWrapper<GrossIndustrialWageData> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("title",dto.getTitle());
            queryWrapper.like("year",dto.getYear());
            Page<GrossIndustrialWageData> result = grossIndustrialWageDataMapper.selectPage(page, queryWrapper);
            return result;
        }
        if ((!StringUtils.isEmpty(dto.getTitle()))){
            QueryWrapper<GrossIndustrialWageData> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("title",dto.getTitle());
            Page<GrossIndustrialWageData> result = grossIndustrialWageDataMapper.selectPage(page, queryWrapper);
            return result;
        }
        if ((!StringUtils.isEmpty(dto.getYear()))){
            QueryWrapper<GrossIndustrialWageData> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("year",dto.getYear());
            Page<GrossIndustrialWageData> result = grossIndustrialWageDataMapper.selectPage(page, queryWrapper);
            return result;
        }
        Page<GrossIndustrialWageData> dataPage = grossIndustrialWageDataMapper.selectPage(page, null);
        return dataPage;
    }

    @Override
    public void removeById(Integer id) {
        int i = grossIndustrialWageDataMapper.deleteById(id);
        if (i<1){
            throw new CustomerException("删除产业信息失败！");
        }
    }

    @Override
    public GrossIndustrialWageData queryById(Integer id) {
        GrossIndustrialWageData grossIndustrialWageData = grossIndustrialWageDataMapper.selectById(id);
        if (null == grossIndustrialWageData){
            throw new CustomerException("当前信息不存在！");
        }
        return grossIndustrialWageData;
    }

    @Override
    public void add(DataAddDto dto) {
        GrossIndustrialWageData grossIndustrialWageData = new GrossIndustrialWageData();
        QueryWrapper<GrossIndustrialWageData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",dto.getTitle());
        queryWrapper.eq("year",dto.getYear());
        List<GrossIndustrialWageData> dataList = grossIndustrialWageDataMapper.selectList(queryWrapper);
        if (dataList.size()>0){
            throw new CustomerException("不能添加同年的同标题数据！");
        }
        Users users = usersMapper.selectById(dto.getUserId());
        BeanUtils.copyProperties(dto,grossIndustrialWageData);
        grossIndustrialWageData.setCreateUser(users.getUsername());
        int insert = grossIndustrialWageDataMapper.insert(grossIndustrialWageData);
        if (insert < 0){
            throw new CustomerException("新增产业数据信息失败！");
        }
    }

    @Override
    public void update(DataUpdateDto dto) {
        GrossIndustrialWageData grossIndustrialWageData1 = grossIndustrialWageDataMapper.selectById(dto.getId());
        GrossIndustrialWageData grossIndustrialWageData = new GrossIndustrialWageData();
        QueryWrapper<GrossIndustrialWageData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",dto.getTitle());
        queryWrapper.eq("year",dto.getYear());
        queryWrapper.ne("id",dto.getId());
        List<GrossIndustrialWageData> grossIndustrialWageDataList = grossIndustrialWageDataMapper.selectList(queryWrapper);
        if (grossIndustrialWageDataList.size()>0){
            throw new CustomerException("不能修改同年的同标题数据！");
        }
        BeanUtils.copyProperties(grossIndustrialWageData1,grossIndustrialWageData);
        grossIndustrialWageData.setTitle(dto.getTitle());
        grossIndustrialWageData.setNumber(dto.getNumber());
        grossIndustrialWageData.setYear(dto.getYear());
        int i = grossIndustrialWageDataMapper.updateById(grossIndustrialWageData);
        if (i < 1){
            throw new CustomerException("修改数据失败");
        }
    }
}
