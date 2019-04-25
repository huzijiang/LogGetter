package com.suixingpay.hw.enterprise.service.impl;


import com.suixingpay.hw.common.core.text.Convert;
import com.suixingpay.hw.enterprise.domain.EnterpriseTargetMakeLineModel;
import com.suixingpay.hw.enterprise.mapper.EnterpriseTargetMakeLineModelMapper;
import com.suixingpay.hw.enterprise.service.IEnterpriseTargetMakeLineModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:49
 **/
@Service
public class EnterpriseTargetMakeLineServiceImpl implements IEnterpriseTargetMakeLineModelService {

    @Autowired
    private EnterpriseTargetMakeLineModelMapper makeLineModelMapper;

    @Override
    public List<EnterpriseTargetMakeLineModel> find(EnterpriseTargetMakeLineModel makeLineModel) {
        return makeLineModelMapper.find(makeLineModel);
    }

    @Override
    public int add(EnterpriseTargetMakeLineModel makeLineModel) {
        return makeLineModelMapper.add(makeLineModel);
    }

    @Override
    public int updateById(EnterpriseTargetMakeLineModel makeLineModel) {
        return makeLineModelMapper.updateById(makeLineModel);
    }

    @Override
    public EnterpriseTargetMakeLineModel findOneById(Integer makeLineModelId) {
        return makeLineModelMapper.findOneById(makeLineModelId);
    }

    @Override
    public int deleteById(Integer makeLineModelId) {
        return makeLineModelMapper.deleteById(makeLineModelId);
    }

    @Override
    public int deleteBatchIds(String ids) {
        Integer[] targetModelIds = Convert.toIntArray(ids);
        return makeLineModelMapper.deleteBatchIds(targetModelIds);
    }
}
