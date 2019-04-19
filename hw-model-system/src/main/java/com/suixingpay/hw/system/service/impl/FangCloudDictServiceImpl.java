package com.suixingpay.hw.system.service.impl;

import com.suixingpay.hw.system.domain.FangCloudDict;
import com.suixingpay.hw.system.mapper.FangCloudDictMapper;
import com.suixingpay.hw.system.service.IFangCloudDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-18 14:30
 **/
@Service
public class FangCloudDictServiceImpl implements IFangCloudDictService {

    @Autowired
    private FangCloudDictMapper fangCloudDictMapper;

    @Override
    public List<FangCloudDict> selectDictDataByDomainName(String domainName) {
        return fangCloudDictMapper.selectDictDataByDomainName(domainName);
    }
}
