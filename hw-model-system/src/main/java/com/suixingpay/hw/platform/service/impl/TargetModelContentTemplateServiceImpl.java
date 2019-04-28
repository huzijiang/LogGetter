package com.suixingpay.hw.platform.service.impl;

import com.suixingpay.hw.common.core.text.Convert;
import com.suixingpay.hw.platform.domain.TargetModelContentTemplate;
import com.suixingpay.hw.platform.mapper.TargetModelContentTemplateMapper;
import com.suixingpay.hw.platform.service.ITargetModelContentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 平台指标模型内容模板 Service 接口实现
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-25 23:00
 **/
@Service
public class TargetModelContentTemplateServiceImpl implements ITargetModelContentTemplateService {
    @Autowired
    private TargetModelContentTemplateMapper targetModelContentTemplateMapper;

    @Override
    public List<TargetModelContentTemplate> find(TargetModelContentTemplate targetModelContentTemplate) {
        return targetModelContentTemplateMapper.find(targetModelContentTemplate);
    }

    @Override
    public int add(TargetModelContentTemplate targetModelContentTemplate) {
        return targetModelContentTemplateMapper.add(targetModelContentTemplate);
    }

    @Override
    public int updateById(TargetModelContentTemplate targetModelContentTemplate) {
        return targetModelContentTemplateMapper.updateById(targetModelContentTemplate);
    }

    @Override
    public TargetModelContentTemplate findOneById(Integer targetModelTemplateId) {
        return targetModelContentTemplateMapper.findOneById(targetModelTemplateId);
    }

    @Override
    public int deleteById(Integer targetModelTemplateId) {
        return targetModelContentTemplateMapper.deleteById(targetModelTemplateId);
    }

    @Override
    public int deleteBatchIds(String ids) {
        return targetModelContentTemplateMapper.deleteBatchIds(Convert.toIntArray(ids));
    }
}
