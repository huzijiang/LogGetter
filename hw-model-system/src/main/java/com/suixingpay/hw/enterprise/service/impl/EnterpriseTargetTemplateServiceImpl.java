package com.suixingpay.hw.enterprise.service.impl;

import com.suixingpay.hw.common.core.text.Convert;
import com.suixingpay.hw.enterprise.domain.EnterpriseTargetTemplate;
import com.suixingpay.hw.enterprise.mapper.EnterpriseTargetTemplateMapper;
import com.suixingpay.hw.enterprise.service.IEnterpriseTargetTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:49
 **/
@Service
public class EnterpriseTargetTemplateServiceImpl implements IEnterpriseTargetTemplateService {

    @Autowired
    private EnterpriseTargetTemplateMapper targetTemplateMapper;

    @Override
    public List<EnterpriseTargetTemplate> find(EnterpriseTargetTemplate template) {
        return targetTemplateMapper.find(template);
    }

    @Override
    public List<EnterpriseTargetTemplate> findList(EnterpriseTargetTemplate template) {
        return targetTemplateMapper.findList(template);
    }

    @Override
    public int add(EnterpriseTargetTemplate template) {
        return targetTemplateMapper.add(template);
    }

    @Override
    public int updateById(EnterpriseTargetTemplate template) {
        return targetTemplateMapper.updateById(template);
    }

    @Override
    public EnterpriseTargetTemplate findOneById(Integer targetTemplateId) {
        return targetTemplateMapper.findOneById(targetTemplateId);
    }

    @Override
    public int deleteById(Integer targetTemplateId) {
        return targetTemplateMapper.deleteById(targetTemplateId);
    }

    @Override
    public int deleteBatchIds(String ids) {
        Integer[] targetModelIds = Convert.toIntArray(ids);
        return targetTemplateMapper.deleteBatchIds(targetModelIds);
    }

    @Override
    public List<EnterpriseTargetTemplate> findAll() {
        return targetTemplateMapper.findAll();
    }
}
