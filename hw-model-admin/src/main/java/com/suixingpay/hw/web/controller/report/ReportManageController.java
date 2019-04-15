package com.suixingpay.hw.web.controller.report;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.report.domain.ReportInfo;
import com.suixingpay.hw.report.service.IReportManageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-08 15:31
 **/
@Controller
@RequestMapping("/report")
public class ReportManageController extends BaseController {
    @Autowired
    private IReportManageService reportManageService;

    @RequiresPermissions("report:view")
    @RequestMapping("/view")
    public String toReportListPage(ModelMap modelMap) {
        // 获取所有企业名称
        List<String> enterpriseList = new ArrayList<>();
        enterpriseList.add("随行付");
        enterpriseList.add("随行付金科");
        enterpriseList.add("银企融合");

        modelMap.put("enterpriseList", enterpriseList);
        return "report/reportList";
    }

    @RequiresPermissions("report:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReportInfo reportInfo) {
        startPage();
        List<ReportInfo> reportInfoList = reportManageService.selectReportInfoList(reportInfo);
        return getDataTable(reportInfoList);
    }

    @RequiresPermissions("report:analysis")
    @RequestMapping("/analysis/{enterpriseReportId}")
    public String analysis(@PathVariable("enterpriseReportId") String enterpriseReportId, ModelMap mmap) {
        mmap.put("testData", "successTest");
        return "report/reportAnalysis";
    }

    @RequiresPermissions("report:changeReportState")
    @RequestMapping("/changeReportState")
    public AjaxResult changeReportState(@RequestParam("reportId") Integer reportId, @RequestParam("state") String state) {
        return AjaxResult.success();
    }

}
