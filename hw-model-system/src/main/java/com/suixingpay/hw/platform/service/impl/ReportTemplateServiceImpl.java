package com.suixingpay.hw.platform.service.impl;

import com.suixingpay.hw.common.core.text.Convert;
import com.suixingpay.hw.platform.domain.ReportTemplate;
import com.suixingpay.hw.platform.mapper.ReportTemplateMapper;
import com.suixingpay.hw.platform.service.IReportTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 平台报告模型 Service
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 15:34
 **/
@Service
public class ReportTemplateServiceImpl implements IReportTemplateService {

    @Autowired
    private ReportTemplateMapper reportTemplateMapper;

    @Override
    public List<ReportTemplate> find(ReportTemplate template) {
        return reportTemplateMapper.find(template);
    }

    @Override
    public int add(ReportTemplate template) {
        return reportTemplateMapper.add(template);
    }

    @Override
    public int updateByReportTemplateId(ReportTemplate template) {
        return reportTemplateMapper.updateByReportTemplateId(template);
    }

    @Override
    public ReportTemplate findOneById(Integer reportTemplateId) {
        return reportTemplateMapper.findOneById(reportTemplateId);
    }

    @Override
    public int deleteById(Integer reportTemplateId) {
        return reportTemplateMapper.deleteById(reportTemplateId);
    }

    @Override
    public List<Integer> selectTargetModelByReportTemplateId(Integer reportTemplateId) {
        return reportTemplateMapper.selectTargetModelByReportTemplateId(reportTemplateId);
    }

    @Override
    public int insertReportTargetRelation(String ids, Integer reportTemplateId) {
        return reportTemplateMapper.insertReportTargetRelation(Convert.toIntArray(ids), reportTemplateId);
    }
}
