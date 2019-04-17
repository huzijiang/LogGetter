package com.suixingpay.hw.web.controller.report;

import com.suixingpay.hw.Starter;
import com.suixingpay.hw.report.domain.ReportInfo;
import com.suixingpay.hw.report.service.IReportManageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-15 15:37
 **/
@SpringBootTest(classes = Starter.class)
@RunWith(value = SpringJUnit4ClassRunner.class)
public class ReportManageControllerTest{

    @Autowired
    private IReportManageService reportManageService;

    @Test
    public void list() {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setReportId(10000001);
        List<ReportInfo> reportInfoList = reportManageService.selectReportInfoList(reportInfo);
        System.out.println(reportInfoList);
    }
}
