package com.suixingpay.hw.report.service;

import com.suixingpay.hw.report.domain.ReportInfo;
import com.suixingpay.hw.report.domain.TargetDataInfo;

import java.util.List;
import java.util.Map;

/**
 * @description: 报告管理
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-11 11:02
 **/
public interface IReportManageService {
    /**
     * 获取企业报告信息列表
     * @param reportInfo 报告信息
     * @return  报告信息列表
     */
    List<ReportInfo> selectReportInfoList(ReportInfo reportInfo);

    /**
     * 获取报告指标数据
     * @param reportId 报告编号
     * @return  报告及其指标数据
     */
    List<ReportInfo> selectReportTargetDataList(Integer reportId);

    /**
     * 报告发布
     * @param mapParam [targetPublishInfoId enterpriseTargetDataId content]
     * @return 变更数
     */
    int batchInsertTargetDataPublishInfo(Map<Integer, TargetDataInfo> mapParam);

    /**
     * 更新企业报告发布状态以及发布者
     * @param publisher 发布者
     * @param enterpriseReportId 报告编号
     * @return  变更数
     */
    int updateReportPublishState(String publisher, Integer enterpriseReportId);
}
