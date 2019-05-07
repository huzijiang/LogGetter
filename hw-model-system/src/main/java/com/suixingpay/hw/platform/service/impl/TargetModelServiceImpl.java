package com.suixingpay.hw.platform.service.impl;

import com.suixingpay.hw.common.core.text.Convert;
import com.suixingpay.hw.platform.domain.TargetModel;
import com.suixingpay.hw.platform.mapper.TargetModelMapper;
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
public class TargetModelServiceImpl implements ITargetModelService {

    @Autowired
    private TargetModelMapper targetModelMapper;

    @Override
    public List<TargetModel> find(TargetModel targetModel) {
        return targetModelMapper.find(targetModel);
    }

    @Override
    public int add(TargetModel targetModel) {
        return targetModelMapper.add(targetModel);
    }

    @Override
    public int updateById(TargetModel targetModel) {
        return targetModelMapper.updateById(targetModel);
    }

    @Override
    public TargetModel findOneById(Integer targetModelId) {
        return targetModelMapper.findOneById(targetModelId);
    }

    @Override
    public int deleteById(Integer targetModelId) {
        return targetModelMapper.deleteById(targetModelId);
    }

    @Override
    public int deleteRelationById(Integer targetModelId) {
        return targetModelMapper.deleteRelationById(targetModelId);
    }
    @Override
    public int deleteBatchIds(String ids) {
        Integer[] targetModelIds = Convert.toIntArray(ids);
        return targetModelMapper.deleteBatchIds(targetModelIds);
    }

    @Override
    public int deleteRelationIds(String ids) {
        Integer[] targetModelIds = Convert.toIntArray(ids);
        return targetModelMapper.deleteRelationIds(targetModelIds);
    }

    @Override
    public int addReportTargetRelation(Integer reportTemplateId, Integer targetModelId) {
        return targetModelMapper.addReportTargetRelation(reportTemplateId, targetModelId);
    }

    @Override
    public List<TargetModel> selectByIds(List<Integer> targetModelIds) {
        return targetModelMapper.selectByIds(targetModelIds);
    }
}
