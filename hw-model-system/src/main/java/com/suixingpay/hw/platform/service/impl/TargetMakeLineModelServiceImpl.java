package com.suixingpay.hw.platform.service.impl;

import com.suixingpay.hw.common.core.text.Convert;
import com.suixingpay.hw.platform.domain.TargetMakeLineModel;
import com.suixingpay.hw.platform.domain.TargetModel;
import com.suixingpay.hw.platform.mapper.TargetMakeLineModelMapper;
import com.suixingpay.hw.platform.mapper.TargetModelMapper;
import com.suixingpay.hw.platform.service.ITargetMakeLineModelService;
import com.suixingpay.hw.platform.service.ITargetModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 15:33
 **/
@Service
public class TargetMakeLineModelServiceImpl implements ITargetMakeLineModelService {

    @Autowired
    private TargetMakeLineModelMapper makeLineModelMapper;

    @Override
    public List<TargetMakeLineModel> find(TargetMakeLineModel makeLineModel) {
        return makeLineModelMapper.find(makeLineModel);
    }

    @Override
    public int add(TargetMakeLineModel makeLineModel) {
        return makeLineModelMapper.add(makeLineModel);
    }

    @Override
    public int updateById(TargetMakeLineModel makeLineModel) {
        return makeLineModelMapper.updateById(makeLineModel);
    }

    @Override
    public TargetMakeLineModel findOneById(Integer makeLineModelId) {
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
