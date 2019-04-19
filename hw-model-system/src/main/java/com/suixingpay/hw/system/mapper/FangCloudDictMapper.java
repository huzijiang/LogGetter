package com.suixingpay.hw.system.mapper;

import com.suixingpay.hw.system.domain.FangCloudDict;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-18 15:10
 **/
public interface FangCloudDictMapper {
    /**
     * 根据字典类型查询字典数据
     *
     * @param domainName 字典类型
     * @return 字典数据集合信息
     */
    List<FangCloudDict> selectDictDataByDomainName(String domainName);
}
