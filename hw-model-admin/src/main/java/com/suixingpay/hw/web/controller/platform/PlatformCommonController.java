package com.suixingpay.hw.web.controller.platform;

import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.platform.domain.TargetModelContentTemplate;
import com.suixingpay.hw.platform.service.IReportTemplateService;
import com.suixingpay.hw.platform.service.ITargetModelContentTemplateService;
import com.suixingpay.hw.platform.service.ITargetModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-25 15:41
 **/
@Controller
public class PlatformCommonController {

    @Autowired
    private IReportTemplateService reportTemplateService;

    @Autowired
    private ITargetModelService targetModelService;

    @Autowired
    private ITargetModelContentTemplateService tmctService;

    /**
     * 进入选择平台指标模型页面
     *
     * @param reportModelId 平台报告模型编号
     * @param modelMap
     * @return
     */
    @RequestMapping("/toAddTargetModelPage/{reportModelId}/{makeCycle}")
    public String addTargetTemplate(@PathVariable("reportModelId") Integer reportModelId, @PathVariable("makeCycle") Integer makeCycle, ModelMap modelMap) {
        modelMap.put("reportModelId", reportModelId);
        modelMap.put("makeCycle", makeCycle);
        //返回已经选好的平台指标模型编号
        modelMap.put("targetModelList", reportTemplateService.selectTargetModelByReportTemplateId(reportModelId));
        return "mini/targetModelMini";
    }

    /**
     * 进入选择平台报告模型页面
     *
     * @return
     */
    @RequestMapping("/toAddReportModelPage")
    public String toReportTemplateMini() {
        return "mini/reportTemplateMini";
    }

    /**
     * 进入选择企业指标模板页面
     *
     * @param enterpriseReportModelId 企业指标模板编号
     * @param modelMap
     * @return
     */
    @RequestMapping("/toAddEnterpriseTargetTempPage/{enterpriseReportModelId}")
    public String toAddEnterpriseTargetTempPage(@PathVariable("enterpriseReportModelId") Integer enterpriseReportModelId, ModelMap modelMap) {
        modelMap.put("enterpriseReportModelId", enterpriseReportModelId);
        return "mini/enterpriseTargetTemplateMini";
    }

    /**
     * 进入选择平台指标模型展示内容模板页面
     *
     * @return
     */
    @RequestMapping("/toAddTMCTPage")
    public String toAddTMCTPage() {
        return "mini/targetModelContentTemplateMini";
    }

    /**
     * 添加 平台指标模型 到 平台报告模型上
     *
     * @param request
     * @return
     */
    @RequestMapping("/addSaveTargetModel")
    @ResponseBody
    public AjaxResult addSaveTargetModel(HttpServletRequest request) {
        String ids = request.getParameter("targetModelIds");
        Integer reportTemplateId = Integer.parseInt(request.getParameter("reportModelId"));
        reportTemplateService.insertReportTargetRelation(ids, reportTemplateId);
        return AjaxResult.success();
    }

    /**
     * 添加 企业指标模板 到 企业报告模板上
     *
     * @param request
     * @return
     */
    @RequestMapping("/addSaveEnterpriseTargetTemp")
    @ResponseBody
    public AjaxResult addSaveEnterpriseTargetTemp(HttpServletRequest request) {
        String ids = request.getParameter("enterpriseTargetTempIds");
        Integer reportTemplateId = Integer.parseInt(request.getParameter("enterpriseReportModelId"));
        reportTemplateService.insertReportTargetRelation(ids, reportTemplateId);
        return AjaxResult.success();
    }

    /**
     * 将选择的单个平台指标模型，回显到父窗口
     *
     * @param targetModelId
     * @param modelMap
     * @return
     */
    @RequestMapping("/getSingleModelDisplay")
    @ResponseBody
    public AjaxResult getSingleModelDisplay(@RequestParam("targetModelId") Integer targetModelId, ModelMap modelMap){
        modelMap.put("name", targetModelService.findOneById(targetModelId).getName());
        modelMap.put("id", targetModelId);
        return AjaxResult.success().put("modelMap", modelMap);
    }

    /**
     * 将选择的单个平台报告模型，回显到父窗口
     *
     * @param reportModelId
     * @param modelMap
     * @return
     */
    @RequestMapping("/getSingleReportModelDisplay")
    @ResponseBody
    public AjaxResult getSingleReportModelDisplay(@RequestParam("reportModelId") Integer reportModelId, ModelMap modelMap){
        modelMap.put("name", reportTemplateService.findOneById(reportModelId).getName());
        modelMap.put("id", reportModelId);
        return AjaxResult.success().put("modelMap", modelMap);
    }

    /**
     * 将选择的单个平台指标模型展示内容模板，回显到父窗口
     *
     * @param targetModelTemplateId
     * @param modelMap
     * @return
     */
    @RequestMapping("/getSingleTMCTDisplay")
    @ResponseBody
    public AjaxResult getSingleTMCTDisplay(@RequestParam("targetModelTemplateId") Integer targetModelTemplateId, ModelMap modelMap){
        TargetModelContentTemplate tmct = tmctService.findOneById(targetModelTemplateId);
        modelMap.put("name", tmct.getName());
        modelMap.put("id", targetModelTemplateId);
        modelMap.put("targetModelId", tmct.getTargetModelId());
        return AjaxResult.success().put("modelMap", modelMap);
    }



    @RequestMapping("/getSingleTemplate")
    @ResponseBody
    public AjaxResult getSingleTargetModel(@RequestParam("id") Integer id, ModelMap modelMap){
        modelMap.put("name", targetModelService.findOneById(id).getName());
        modelMap.put("id", id);
        return AjaxResult.success().put("modelMap", modelMap);
    }

    @RequestMapping("/getSingleReportTemplate")
    @ResponseBody
    public AjaxResult getSingleReportTemplate(@RequestParam("id") Integer id, ModelMap modelMap){
        modelMap.put("name", reportTemplateService.findOneById(id).getName());
        modelMap.put("id", id);
        return AjaxResult.success().put("modelMap", modelMap);
    }


}
