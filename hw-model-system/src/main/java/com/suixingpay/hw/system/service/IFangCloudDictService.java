package com.suixingpay.hw.system.service;

import com.suixingpay.hw.system.domain.FangCloudDict;

import java.util.List;

public interface IFangCloudDictService {

    /**
     * 根据字典类型查询字典数据
     *
     * @param domainName 字典类型
     * @return 字典数据集合信息
     */
    List<FangCloudDict> selectDictDataByDomainName(String domainName);
}
