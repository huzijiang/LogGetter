package com.suixingpay.hw.report.service.impl;

import com.suixingpay.hw.report.domain.ReportInfo;
import com.suixingpay.hw.report.domain.TargetDataInfo;
import com.suixingpay.hw.report.mapper.ReportManageMapper;
import com.suixingpay.hw.report.service.IReportManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 报告管理服务实现
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-11 15:17
 **/
@Service
public class ReportManageServiceImpl implements IReportManageService {

    @Autowired
    private ReportManageMapper reportManageMapper;

    @Override
    public List<ReportInfo> selectReportInfoList(ReportInfo reportInfo) {
        return reportManageMapper.selectReportInfoList(reportInfo);
    }

    @Override
    public List<ReportInfo> selectReportTargetDataList(Integer reportId) {
        return reportManageMapper.selectReportTargetDataList(reportId);
    }

    @Override
    public int batchInsertTargetDataPublishInfo(Map<Integer, TargetDataInfo> mapParam) {
        return reportManageMapper.batchInsertTargetDataPublishInfo(mapParam);
    }

    @Override
    public int updateReportPublishState(String publisher, String publishState, Integer enterpriseReportId) {
        return reportManageMapper.updateReportPublishState(publisher, publishState, enterpriseReportId);
    }

    @Override
    public int updateEnterpriseTargetDataPublishState(List<TargetDataInfo> targetDataInfoList) {
        return reportManageMapper.updateEnterpriseTargetDataPublishState(targetDataInfoList);
    }

    @Override
    public int insertEnterpriseReportPublishInfo(Integer reportPublishInfoId, String reportPublishContent,
            Integer enterpriseReportId) {
        return reportManageMapper.insertEnterpriseReportPublishInfo(reportPublishInfoId, reportPublishContent, enterpriseReportId);
    }

    @Override
    public List<ReportInfo> selectEntReportByEntId(Integer enterpriseId) {
        return reportManageMapper.selectEntReportByEntId(enterpriseId);
    }

    @Override
    public void deleteReportPublishInfoByReportId(Integer reportId) {
        reportManageMapper.deleteReportPublishInfoByReportId(reportId);
    }

    @Override
    public void deleteTargetPublishInfoByTargetIds(List<Integer> targetIds) {
        reportManageMapper.deleteTargetPublishInfoByTargetIds(targetIds);
    }
}
