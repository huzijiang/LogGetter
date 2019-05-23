package com.suixingpay.hw.web.controller.report;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.domain.RpcResponseBean;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.common.utils.JsonUtils;
import com.suixingpay.hw.enterprise.service.IEnterpriseReportTemplateService;
import com.suixingpay.hw.framework.util.ShiroUtils;
import com.suixingpay.hw.report.domain.ReportInfo;
import com.suixingpay.hw.report.domain.TargetDataInfo;
import com.suixingpay.hw.report.service.IReportManageService;
import com.suixingpay.hw.web.service.SaasRemoteService;
import com.suixingpay.hw.web.util.IdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private SaasRemoteService saasRemoteService;

    @Autowired
    private IEnterpriseReportTemplateService entReportTempService;

    //加载前端页面地址
    @Value("${fangcloud.saas.url}")
    private String saasUrl;

    /**
     * 进入报告列表页面
     *
     * @return 报告列表页面
     */
    @RequiresPermissions("report:view")
    @RequestMapping("/view")
    public String toReportListPage() {
        return "report/reportPublishList";
    }

    /**
     * 获取报告列表
     *
     * @param reportInfo 搜索条件
     * @return 分页好的结果集
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
     * 进入报告分析/查看页面
     *
     * @param enterpriseReportId 企业报告模板编号
     * @param type 区分：type = analysis 分析页面、type = detail 查看页面
     * @param modelMap ModelMap
     * @return 报告分析/查看页面
     */
    @RequiresPermissions("report:analysis")
    @RequestMapping("/analysis/{enterpriseReportId}/{type}")
    public String analysis(@PathVariable("enterpriseReportId") Integer enterpriseReportId, @PathVariable("type") String type, ModelMap modelMap) {
        List<ReportInfo> reportTargetDataList = reportManageService.selectReportTargetDataList(enterpriseReportId);

        modelMap.put("type", type);
        modelMap.put("reportTargetDataList", reportTargetDataList);
        return "report/reportAnalysis";
    }

    /**
     * 报告发布
     *
     * @param jsonStr 入库数据
     * @return 响应信息
     */
    @RequiresPermissions("report:publish")
    @RequestMapping(value = "/publish")
    @ResponseBody
    public AjaxResult reportPublish(@RequestBody String jsonStr) {
        try {
            Map<String, Object> map = JsonUtils.fromJson(jsonStr);
            Integer enterpriseReportId = (Integer) map.get("enterpriseReportId");
            String reportPublishContent = (String) map.get("reportPublishContent");
            logger.info(">>>报告发布,报告编号：[{}]", enterpriseReportId);
            Map<Integer, TargetDataInfo> mapParam = new HashMap<>();
            List<TargetDataInfo> targetDataInfoList = new ArrayList<>();
            List<Integer> targetIds = new ArrayList<>();

            JSONArray jsonArray = (JSONArray) map.get("paramData");
            for (int i = 0; i < jsonArray.size(); i++) {
                TargetDataInfo targetDataInfo = new TargetDataInfo();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int enterpriseTargetDataId = Integer.parseInt((String) jsonObject.get("enterpriseTargetDataId"));
                targetIds.add(enterpriseTargetDataId);

                targetDataInfo.setPublisher(ShiroUtils.getUserId().intValue());
                targetDataInfo.setEnterpriseTargetDataId(enterpriseTargetDataId);
                targetDataInfo.setPublishState("01");
                targetDataInfo.setTargetDataPublishContent((String) jsonObject.get("targetDataPublishContent"));
                mapParam.put((Integer) IdUtil.getManyId("t_enterprise_target_data_publish_info",1).get(0), targetDataInfo);
                targetDataInfoList.add(targetDataInfo);
            }
            //删除：报告发布表中之前发布的内容
            reportManageService.deleteReportPublishInfoByReportId(enterpriseReportId);
            //删除：报告对应的指标发布表中之前发布的内容
            reportManageService.deleteTargetPublishInfoByTargetIds(targetIds);

            //插入：报告对应的指标发布表中
            reportManageService.batchInsertTargetDataPublishInfo(mapParam);
            //更新：报告表发布状态
            reportManageService.updateReportPublishState(ShiroUtils.getLoginName(), "01", enterpriseReportId);
            //更新：报告对应的指标发布状态
            reportManageService.updateEnterpriseTargetDataPublishState(targetDataInfoList);
            //插入：报告对应的指标发布表中
            reportManageService.insertEnterpriseReportPublishInfo((Integer) IdUtil.getManyId("t_enterprise_report_publish_info",1).get(0), reportPublishContent, enterpriseReportId);
        }catch (Exception e) {
            logger.error("发布异常：[{}]", e.getMessage());
            return AjaxResult.error("发布出现异常！请联系相关人员解决！");
        }
        return AjaxResult.success();
    }

    /**
     * 再次发布报告：仅仅是将报告状态值：发布（01）变为未发布（00）
     *
     * @param reportId 报告编号
     * @return 响应信息
     */
    @RequestMapping("/publishAgain/{id}")
    @ResponseBody
    public AjaxResult publishAgain(@PathVariable("id") Integer reportId) {
        return toAjax(reportManageService.updateReportPublishState(ShiroUtils.getLoginName(), "00", reportId));
    }


    @RequiresPermissions("report:iframePublishOrEmail")
    @RequestMapping("/iframePublishOrEmail/{reportId}&&{entReportTempId}&&{from}&&{enterpriseId}")
    public String iframePublish(@PathVariable("reportId") Integer reportId, @PathVariable("entReportTempId") Integer entReportTempId,
                                @PathVariable("from") String from, @PathVariable("enterpriseId") Integer enterpriseId,
            ModelMap modelMap, HttpServletResponse response) {
        RpcResponseBean<Map<String, String>> demoToken = saasRemoteService.testLogin(response);

        //企业报告编号
        modelMap.put("reportId", reportId);
        //企业报告模板名称
        modelMap.put("entReportName", entReportTempService.findOneById(entReportTempId).getName());
        modelMap.put("token", demoToken.getData().get("token"));
        modelMap.put("from", from);
        modelMap.put("enterpriseId", enterpriseId);
        modelMap.put("saasUrl", saasUrl);

        return "report/reportPublishOrEmail";
    }
}
