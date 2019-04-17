package com.suixingpay.hw.web.controller.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.common.utils.JsonUtils;
import com.suixingpay.hw.framework.util.ShiroUtils;
import com.suixingpay.hw.report.domain.ReportInfo;
import com.suixingpay.hw.report.domain.TargetDataInfo;
import com.suixingpay.hw.report.service.IReportManageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
    public String toReportListPage() {

        return "report/reportList";
    }

    @RequiresPermissions("report:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReportInfo reportInfo, ModelMap modelMap) {
        // 获取所有企业名称
        List<String> enterpriseList = new ArrayList<>();
        enterpriseList.add("随行付");
        enterpriseList.add("随行付金科");
        enterpriseList.add("银企融合");

        modelMap.put("enterpriseList", enterpriseList);
        startPage();
        List<ReportInfo> reportInfoList = reportManageService.selectReportInfoList(reportInfo);
        return getDataTable(reportInfoList);
    }

    @RequiresPermissions("report:analysis")
    @RequestMapping("/analysis/{enterpriseReportId}")
    public String analysis(@PathVariable("enterpriseReportId") Integer enterpriseReportId, ModelMap mmap) {
        List<ReportInfo> reportTargetDataList = reportManageService.selectReportTargetDataList(enterpriseReportId);
        mmap.put("reportTargetDataList", reportTargetDataList);
        return "report/reportAnalysis";
    }

    @RequiresPermissions("report:publish")
    @RequestMapping(value = "/publish")
    @ResponseBody
    public AjaxResult reportPublish(@RequestBody String jsonStr) {
        logger.info(">>>报告发布[{}]", jsonStr);
        try {
            Map<String, Object> map = JsonUtils.fromJson(jsonStr);
            map.forEach((key, value) -> {
                System.out.println(key + " = " + value);
            });
            Integer enterpriseReportId = (Integer) map.get("enterpriseReportId");
            Map<Integer, TargetDataInfo> mapParam = new HashMap<>();

            JSONArray jsonArray = (JSONArray) map.get("paramData");
            for (int i = 0; i < jsonArray.size(); i++) {
                TargetDataInfo targetDataInfo = new TargetDataInfo();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                targetDataInfo.setEnterpriseTargetDataId(Integer.parseInt((String) jsonObject.get("enterpriseTargetDataId")));
                targetDataInfo.setTargetDataPublishContent((String) jsonObject.get("targetDataPublishContent"));
                mapParam.put(1000099+i, targetDataInfo);
            }

            reportManageService.batchInsertTargetDataPublishInfo(mapParam);
            reportManageService.updateReportPublishState(ShiroUtils.getLoginName(), enterpriseReportId);
        }catch (Exception e) {
            return AjaxResult.error("发布指标出现异常！请联系相关人员解决！");
        }
        return AjaxResult.success();
    }

    @RequiresPermissions("report:changeReportState")
    @RequestMapping("/changeReportState")
    public AjaxResult changeReportState(@RequestParam("reportId") Integer reportId, @RequestParam("state") String state) {
        return AjaxResult.success();
    }

}
