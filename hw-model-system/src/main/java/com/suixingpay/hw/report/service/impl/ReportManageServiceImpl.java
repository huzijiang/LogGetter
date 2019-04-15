package com.suixingpay.hw.report.service.impl;

import com.suixingpay.hw.report.domain.ReportInfo;
import com.suixingpay.hw.report.mapper.ReportManageMapper;
import com.suixingpay.hw.report.service.IReportManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        ReportInfo reportInfo1 = new ReportInfo();
        reportInfo1.setEnterpriseId(10001);
        reportInfo1.setEnterpriseName("随行付");
        reportInfo1.setReportId(9000001);
        reportInfo1.setReportTemplateId(500000001);
        reportInfo1.setState("00");
        reportInfo1.setPublishState("01");
        reportInfo1.setMakeDate(new Date());

        ReportInfo reportInfo2 = new ReportInfo();
        reportInfo2.setEnterpriseId(10002);
        reportInfo2.setEnterpriseName("随行付金科");
        reportInfo2.setReportId(9000002);
        reportInfo2.setReportTemplateId(500000002);
        reportInfo2.setState("00");
        reportInfo2.setPublishState("01");
        reportInfo2.setMakeDate(new Date());

        List<ReportInfo> reportInfoList = new ArrayList<>();
        reportInfoList.add(reportInfo1);
        reportInfoList.add(reportInfo2);

        //return reportManageMapper.selectReportInfoList(reportInfo);
        return reportInfoList;
    }
}
