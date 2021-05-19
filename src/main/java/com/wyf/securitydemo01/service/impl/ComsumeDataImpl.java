package com.wyf.securitydemo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.entity.dto.DataAddDto;
import com.wyf.securitydemo01.entity.dto.DataDto;
import com.wyf.securitydemo01.entity.dto.DataUpdateDto;
import com.wyf.securitydemo01.entity.pojo.ComsumeData;
import com.wyf.securitydemo01.entity.pojo.OdSysLog;
import com.wyf.securitydemo01.entity.pojo.Users;
import com.wyf.securitydemo01.exceptionHandle.CustomerException;
import com.wyf.securitydemo01.mapper.ComsumeDataMapper;
import com.wyf.securitydemo01.mapper.UsersMapper;
import com.wyf.securitydemo01.service.ComsumeDataService;
import com.wyf.securitydemo01.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: wyf
 * @Date: 2021/5/19 10:21
 */
@Service
@Slf4j
public class ComsumeDataImpl implements ComsumeDataService {

    @Autowired
    private ComsumeDataMapper comsumeDataMapper;
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public Page<ComsumeData> queryCDataPage(DataDto dto) {
        Page<ComsumeData> page = new Page<>();
        page.setSize(dto.getSize());
        page.setCurrent(dto.getCurrent());
        if ((!StringUtils.isEmpty(dto.getTitle()) && (!StringUtils.isEmpty(dto.getYear())))) {
            QueryWrapper<ComsumeData> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("title", dto.getTitle());
            queryWrapper.like("year", dto.getYear());
            Page<ComsumeData> result = comsumeDataMapper.selectPage(page, queryWrapper);
            return result;
        }
        if ((!StringUtils.isEmpty(dto.getTitle()))) {
            QueryWrapper<ComsumeData> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("title", dto.getTitle());
            Page<ComsumeData> result = comsumeDataMapper.selectPage(page, queryWrapper);
            return result;
        }
        if ((!StringUtils.isEmpty(dto.getYear()))) {
            QueryWrapper<ComsumeData> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("year", dto.getYear());
            Page<ComsumeData> result = comsumeDataMapper.selectPage(page, queryWrapper);
            return result;
        }
        Page<ComsumeData> dataPage = comsumeDataMapper.selectPage(page, null);
        return dataPage;
    }

    @Override
    public void removeById(Integer id) {
        int i = comsumeDataMapper.deleteById(id);
        if (i < 1) {
            throw new CustomerException("删除消费信息失败！");
        }
    }

    @Override
    public ComsumeData queryById(Integer id) {
        ComsumeData comsumeData = comsumeDataMapper.selectById(id);
        if (null == comsumeData) {
            throw new CustomerException("当前信息不存在！");
        }
        return comsumeData;
    }

    @Override
    public void add(DataAddDto dto) {
        ComsumeData comsumeData = new ComsumeData();
        QueryWrapper<ComsumeData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",dto.getTitle());
        queryWrapper.eq("year",dto.getYear());
        List<ComsumeData> dataList = comsumeDataMapper.selectList(queryWrapper);
        if (dataList.size()>0){
            throw new CustomerException("不能添加同年的同标题数据！");
        }
        Users users = usersMapper.selectById(dto.getUserId());
        BeanUtils.copyProperties(dto,comsumeData);
        comsumeData.setCreateUser(users.getUsername());
        int insert = comsumeDataMapper.insert(comsumeData);
        if (insert < 0){
            throw new CustomerException("新增消费数据信息失败！");
        }
    }

    @Override
    public void update(DataUpdateDto dto) {
        ComsumeData comsumeData1 = comsumeDataMapper.selectById(dto.getId());
        ComsumeData comsumeData = new ComsumeData();
        QueryWrapper<ComsumeData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", dto.getTitle());
        queryWrapper.eq("year", dto.getYear());
        queryWrapper.ne("id", dto.getId());
        List<ComsumeData> comsumeDataList = comsumeDataMapper.selectList(queryWrapper);
        if (comsumeDataList.size() > 0) {
            throw new CustomerException("不能修改同年的同标题数据！");
        }
        BeanUtils.copyProperties(comsumeData1,comsumeData);
        comsumeData.setYear(dto.getYear());
        comsumeData.setTitle(dto.getTitle());
        comsumeData.setNumber(dto.getNumber());
        int i = comsumeDataMapper.updateById(comsumeData);
        if (i < 1) {
            throw new CustomerException("修改数据失败");
        }
    }
}
