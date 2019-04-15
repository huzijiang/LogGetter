package com.suixingpay.hw.report.mapper;

import com.suixingpay.hw.report.domain.ReportInfo;

import java.util.List;

/**
 * @description: 报告管理
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-11 11:07
 **/
public interface ReportManageMapper {
    /**
     * 获取企业报告信息列表
     * @param reportInfo 报告信息
     * @return  报告信息列表
     */
    List<ReportInfo> selectReportInfoList(ReportInfo reportInfo);
}
