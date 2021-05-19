package com.wyf.securitydemo01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.securitydemo01.entity.dto.DataAddDto;
import com.wyf.securitydemo01.entity.dto.DataDto;
import com.wyf.securitydemo01.entity.dto.DataUpdateDto;
import com.wyf.securitydemo01.entity.pojo.GrossIndustrialWageData;

/**
 * @Author: wyf
 * @Date: 2021/5/19 10:13
 */
public interface GrossIndustrialWageDataService {
    Page<GrossIndustrialWageData> queryDWDataPage(DataDto dto);

    void removeById(Integer id);

    GrossIndustrialWageData queryById(Integer id);

    void add(DataAddDto dto);

    void update(DataUpdateDto dto);
}
