package com.suixingpay.hw.framework.web.service;

import com.suixingpay.hw.system.domain.FangCloudDict;
import com.suixingpay.hw.system.service.IFangCloudDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-19 13:45
 **/
@Service("fangCloudDict")
public class FangCloudDictService {
    @Autowired
    private IFangCloudDictService fangCloudDictService;

    /**
     * 根据字典类型查询字典数据
     *
     * @param domainName 字典类型
     * @return 字典数据集合信息
     */
    public List<FangCloudDict> getType(String domainName) {
        return fangCloudDictService.selectDictDataByDomainName(domainName);
    }
}
