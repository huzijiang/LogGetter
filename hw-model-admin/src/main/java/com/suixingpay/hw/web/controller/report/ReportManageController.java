package com.suixingpay.hw.web.controller.report;

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
import com.suixingpay.hw.system.domain.Enterprise;
import com.suixingpay.hw.system.service.IEnterpriseService;
import com.suixingpay.hw.web.util.IdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @description: 报告管理 controller
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-08 15:31
 **/
@Controller
@RequestMapping("/report")
public class ReportManageController extends BaseController {

    @Autowired
    private IReportManageService reportManageService;

    @Autowired
    private IEnterpriseService enterpriseService;

    /**
     * 进入报告列表页面
     *
     * @param modelMap
     * @return
     */
    @RequiresPermissions("report:view")
    @RequestMapping("/view")
    public String toReportListPage(ModelMap modelMap) {
        // 获取所有企业名称
        List<Enterprise> enterpriseList = enterpriseService.selectEnterpriseList(new Enterprise());
        modelMap.put("enterpriseList", enterpriseList);
        return "report/reportList";
    }

    /**
     * 获取报告列表
     *
     * @param reportInfo
     * @return
     */
    @RequiresPermissions("report:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReportInfo reportInfo) {
        startPage();
        List<ReportInfo> reportInfoList = reportManageService.selectReportInfoList(reportInfo);
        return getDataTable(reportInfoList);
    }

    /**
     * 进入报告分析页面
     *
     * @param enterpriseReportId
     * @param mmap
     * @return
     */
    @RequiresPermissions("report:analysis")
    @RequestMapping("/analysis/{enterpriseReportId}")
    public String analysis(@PathVariable("enterpriseReportId") Integer enterpriseReportId, ModelMap mmap) {
        List<ReportInfo> reportTargetDataList = reportManageService.selectReportTargetDataList(enterpriseReportId);
        mmap.put("reportTargetDataList", reportTargetDataList);
        return "report/reportAnalysis";
    }

    /**
     * 报告发布
     *
     * @param jsonStr
     * @return
     */
    @RequiresPermissions("report:publish")
    @RequestMapping(value = "/publish")
    @ResponseBody
    public AjaxResult reportPublish(@RequestBody String jsonStr) {
        logger.info(">>>报告发布[{}]", jsonStr);
        try {
            Map<String, Object> map = JsonUtils.fromJson(jsonStr);
            Integer enterpriseReportId = (Integer) map.get("enterpriseReportId");
            String reportPublishContent = (String) map.get("reportPublishContent");

            Map<Integer, TargetDataInfo> mapParam = new HashMap<>();
            List<TargetDataInfo> targetDataInfoList = new ArrayList<>();

            JSONArray jsonArray = (JSONArray) map.get("paramData");
            for (int i = 0; i < jsonArray.size(); i++) {
                TargetDataInfo targetDataInfo = new TargetDataInfo();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                targetDataInfo.setPublisher(ShiroUtils.getUserId().intValue());
                targetDataInfo.setEnterpriseTargetDataId(Integer.parseInt((String) jsonObject.get("enterpriseTargetDataId")));
                targetDataInfo.setTargetDataPublishContent((String) jsonObject.get("targetDataPublishContent"));
                mapParam.put((Integer) IdUtil.getManyId("t_enterprise_target_data_publish_info",1).get(0), targetDataInfo);
                targetDataInfoList.add(targetDataInfo);
            }

            reportManageService.batchInsertTargetDataPublishInfo(mapParam);
            reportManageService.updateReportPublishState(ShiroUtils.getLoginName(), enterpriseReportId);
            reportManageService.updateEnterpriseTargetDataPublishState(targetDataInfoList);
            reportManageService.insertEnterpriseReportPublishInfo((Integer) IdUtil.getManyId("t_enterprise_report_publish_info",1).get(0), reportPublishContent, enterpriseReportId);
        }catch (Exception e) {
            return AjaxResult.error("发布指标出现异常！请联系相关人员解决！");
        }
        return AjaxResult.success();
    }

    /**
     * 更新报告应用状态
     *
     * @param reportId
     * @param state
     * @return
     */
    @RequiresPermissions("report:changeReportState")
    @RequestMapping("/changeReportState")
    public AjaxResult changeReportState(@RequestParam("reportId") Integer reportId, @RequestParam("state") String state) {
        return AjaxResult.success();
    }
}
