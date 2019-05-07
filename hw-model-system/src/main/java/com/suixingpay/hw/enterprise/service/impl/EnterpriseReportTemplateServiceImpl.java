package com.suixingpay.hw.enterprise.service.impl;

import com.suixingpay.hw.enterprise.domain.EnterpriseReportTemplate;
import com.suixingpay.hw.enterprise.mapper.EnterpriseReportTemplateMapper;
import com.suixingpay.hw.enterprise.service.IEnterpriseReportTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:49
 **/
@Service
public class EnterpriseReportTemplateServiceImpl implements IEnterpriseReportTemplateService {

    @Autowired
    private EnterpriseReportTemplateMapper enterpriseReportTemplateMapper;

    @Override
    public List<EnterpriseReportTemplate> find(EnterpriseReportTemplate template) {
        return enterpriseReportTemplateMapper.find(template);
    }

    @Override
    public int add(EnterpriseReportTemplate template) {
        return enterpriseReportTemplateMapper.add(template);
    }

    @Override
    public int updateById(EnterpriseReportTemplate template) {
        return enterpriseReportTemplateMapper.updateById(template);
    }

    @Override
    public EnterpriseReportTemplate findOneById(Integer reportTemplateId) {
        return enterpriseReportTemplateMapper.findOneById(reportTemplateId);
    }

    @Override
    public int deleteById(Integer reportTemplateId) {
        return enterpriseReportTemplateMapper.deleteById(reportTemplateId);
    }

    @Override
    public List<EnterpriseReportTemplate> findAll() {
        return null;
    }
}
