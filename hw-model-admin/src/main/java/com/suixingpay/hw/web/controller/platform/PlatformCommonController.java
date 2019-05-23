package com.suixingpay.hw.web.controller.platform;

import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.utils.StringUtils;
import com.suixingpay.hw.enterprise.domain.Enterprise;
import com.suixingpay.hw.enterprise.domain.EnterpriseReportTemplate;
import com.suixingpay.hw.enterprise.domain.EnterpriseTargetTemplate;
import com.suixingpay.hw.enterprise.service.*;
import com.suixingpay.hw.platform.domain.TargetModelContentTemplate;
import com.suixingpay.hw.platform.service.IReportTemplateService;
import com.suixingpay.hw.platform.service.ITargetMakeLineModelService;
import com.suixingpay.hw.platform.service.ITargetModelContentTemplateService;
import com.suixingpay.hw.platform.service.ITargetModelService;
import com.suixingpay.hw.report.domain.ReportInfo;
import com.suixingpay.hw.report.service.IReportManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private IEnterpriseReportTemplateService entReportTempService;

    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private IEnterpriseTargetTemplateService entTargetTempService;

    @Autowired
    private IEnterpriseOrgTreeService entOrgTreeService;

    @Autowired
    private IEnterpriseTargetMakeLineModelService entTMLModelService;

    @Autowired
    private ITargetMakeLineModelService targetMakeLineModelService;

    @Autowired
    private IReportManageService reportManageService;

    /**
     * 进入选择平台指标模型页面
     * 注：makeCycle == 999 表示没有此周期，传入前台表示查询全部指标
     *
     * @param reportModelId 平台报告模型编号
     * @param modelMap
     * @return
     */
    @RequestMapping("/toAddTargetModelPage/{reportModelId}/{makeCycle}")
    public String addTargetTemplate(@PathVariable("reportModelId") Integer reportModelId, @PathVariable("makeCycle") Integer makeCycle, ModelMap modelMap) {
        modelMap.put("reportModelId", reportModelId);
        modelMap.put("makeCycle", makeCycle == 999 ? null:makeCycle);
        //返回在库中已经存在的平台指标模型编号
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
     * 进入选择企业报告模板页面
     *
     * @return
     */
    @RequestMapping("/toAddEnterpriseReportTempPage")
    public String toAddEnterpriseReportTempPage() {
        return "mini/enterpriseReportTemplateMini";
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

        //先删除库中已经存在的 平台指标模型 到 平台报告模型 的映射关系
        if (!StringUtils.isEmpty(ids)) {
            reportTemplateService.deleteReportTargetRelation(reportTemplateId);
        }
        //再插入现在建立的映射关系
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
//        modelMap.put("name", reportTemplateService.findOneById(reportModelId).getName());
//        modelMap.put("id", reportModelId);
        modelMap.put("reportModel", reportTemplateService.findOneById(reportModelId));
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

    /**
     * 将选择的单个企业报告模板，回显到父窗口
     *
     * @param entReportTempId
     * @param modelMap
     * @return
     */
    @RequestMapping("/getSingleEntReportTempDisplay")
    @ResponseBody
    public AjaxResult getSingleEntReportTempDisplay(@RequestParam("entReportTempId") Integer entReportTempId, ModelMap modelMap){
//        modelMap.put("name", entReportTempService.findOneById(entReportTempId).getName());
//        modelMap.put("id", entReportTempId);
        modelMap.put("entReportTemp", entReportTempService.findOneById(entReportTempId));
        return AjaxResult.success().put("modelMap", modelMap);
    }

    /**
     * 进入：导入企业指标标记线模板页面
     */
    @RequestMapping("toExportETtMLModelPage")
    public String toExportETtMLModelPage(ModelMap modelMap) {
        // 获取所有企业名称
        List<Enterprise> enterpriseList = enterpriseService.selectEnterpriseList(new Enterprise());
        modelMap.put("enterpriseList", enterpriseList);
        return "mini/exportETMLModelPage";
    }

    /**
     * 根据 企业编号 获取 企业报告模板
     * @param enterpriseId 企业编号
     * @return
     */
    @RequestMapping("/getEntReportTemp")
    @ResponseBody
    public AjaxResult getEntReportTemp(@RequestParam("enterpriseId")  Integer enterpriseId) {
        EnterpriseReportTemplate template = new EnterpriseReportTemplate();
        template.setEnterpriseId(enterpriseId);
        return AjaxResult.success().put("entReportTempList", entReportTempService.find(template));
    }

    /**
     * 根据 企业编号 获取 企业报告
     * @param enterpriseId 企业编号
     * @return
     */
    @RequestMapping("/getEntReport")
    @ResponseBody
    public AjaxResult getEntReport(@RequestParam("enterpriseId") Integer enterpriseId, @RequestParam("makeCycle") String makeCycle) {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setEnterpriseId(enterpriseId);
        reportInfo.setMakeCycle(makeCycle);
        return AjaxResult.success().put("entReportList", reportManageService.selectReportInfoList(reportInfo));
    }

    /**
     * 根据 企业报告模板编号 获取 企业指标模板
     * @param entReportTempId 企业报告模板编号
     * @return
     */
    @RequestMapping("/getEntTargetTemp")
    @ResponseBody
    public AjaxResult getEntTargetTemp(@RequestParam("entReportTempId")  Integer entReportTempId) {
        EnterpriseTargetTemplate template = new EnterpriseTargetTemplate();
        template.setEnterpriseReportTemplateId(entReportTempId);
        return AjaxResult.success().put("entTargetTempList", entTargetTempService.find(template));
    }

    /**
     * 根据 平台企业编号 获取 组织结构
     *
     * @param platformEntId 平台企业编号
     * @return
     */
    @RequestMapping("/getOrgList")
    @ResponseBody
    public AjaxResult getOrg(@RequestParam("platformEntId")  Integer platformEntId) {
        return AjaxResult.success().put("orgList", entOrgTreeService.findByPlatformEntId(platformEntId));
    }

    /**
     * 根据 平台指标模型编号 获取 平台指标展示模型
     *
     * @param targetModelId 平台指标模型编号
     * @return
     */
    @RequestMapping("/getTargetModelTempList")
    @ResponseBody
    public AjaxResult getTargetModelTempList(@RequestParam("targetModelId")  Integer targetModelId) {
        return AjaxResult.success().put("targetModelTempList", tmctService.selectByTargetModelId(targetModelId));
    }

    /**
     * 根据 企业指标模型编号 获取 企业指标标记线模板
     *
     * @param entTargetTempId 企业指标模型编号
     * @return
     */
    @RequestMapping("/getEntMakeLineList")
    @ResponseBody
    public AjaxResult getEntMakeLineList(@RequestParam("entTargetTempId")  Integer entTargetTempId) {
        return AjaxResult.success().put("entMakeLineList", entTMLModelService.selectByEntTargetTempId(entTargetTempId));
    }

    /**
     * 根据 指标模型展示编号 获取 指标标记线模型
     *
     * @param targetModelTempId 企业指标模型编号
     * @return
     */
    @RequestMapping("/getMakeLineList")
    @ResponseBody
    public AjaxResult getMakeLineList(@RequestParam("targetModelTempId")  Integer targetModelTempId) {
        return AjaxResult.success().put("makeLineList", targetMakeLineModelService.selectByTargetModelTempId(targetModelTempId));
    }

}
