package com.suixingpay.hw.web.controller.enterprise;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.enterprise.domain.Enterprise;
import com.suixingpay.hw.enterprise.domain.EnterpriseOrgTree;
import com.suixingpay.hw.enterprise.domain.EnterpriseReportTemplate;
import com.suixingpay.hw.enterprise.service.IEnterpriseOrgTreeService;
import com.suixingpay.hw.enterprise.service.IEnterpriseReportTemplateService;
import com.suixingpay.hw.enterprise.service.IEnterpriseService;
import com.suixingpay.hw.framework.util.ShiroUtils;
import com.suixingpay.hw.platform.domain.ReportTemplate;
import com.suixingpay.hw.platform.service.IReportTemplateService;
import com.suixingpay.hw.platform.service.ITargetModelService;
import com.suixingpay.hw.web.util.IdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:52
 **/
@Controller
@RequestMapping("/enterprise/reportTemplate")
public class EnterpriseReportTemplateController  extends BaseController {

    @Autowired
    private IEnterpriseReportTemplateService reportTemplateService;

    @Autowired
    private IReportTemplateService templateService;

    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private IEnterpriseOrgTreeService enterpriseOrgTreeService;

    @Autowired
    private ITargetModelService targetModelService;

    /**
     * 进入报告模板列表页面
     */
    @RequiresPermissions("enterprise:reportTemplate:view")
    @RequestMapping("/view")
    public String view() {
        return "enterprise/report/enterpriseReportTemplate";
    }

    /**
     * 获取报告模板列表
     */
    @RequiresPermissions("enterprise:reportTemplate:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(EnterpriseReportTemplate template) {
        startPage();
        List<EnterpriseReportTemplate> reportTemplateList = reportTemplateService.find(template);
        return getDataTable(reportTemplateList);
    }

    /**
     * 进入报告模板添加页面
     */
    @RequestMapping("/add")
    public String add(ModelMap mmap) {
        List<Enterprise> enterpriseList = enterpriseService.selectEnterpriseList(new Enterprise());
        mmap.put("enterpriseList", enterpriseList);
        return "enterprise/report/enterpriseReportTemplateAdd";
    }

    /**
     * 新增报告模板
     */
    @RequiresPermissions("enterprise:reportTemplate:save")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult addSave(EnterpriseReportTemplate template) {
        template.setEnterpriseReportTemplateId((Integer) IdUtil.getManyId("t_enterprise_report_template",1).get(0));
        template.setCreateDate(new Date());
        template.setCreater(ShiroUtils.getUserId().intValue());
        return toAjax(reportTemplateService.add(template));
    }

    /**
     * 修改报告模板
     */
    @RequestMapping("/edit/{enterpriseReportTemplateId}")
    public String edit(@PathVariable("enterpriseReportTemplateId") Integer enterpriseReportTemplateId, ModelMap mmap) {
        mmap.put("enterpriseReportTemplate", reportTemplateService.findOneById(enterpriseReportTemplateId));
        return "enterprise/report/enterpriseReportTemplateEdit";
    }

    /**
     * 修改保存报告模板
     */
    @RequiresPermissions("enterprise:reportTemplate:edit")
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(EnterpriseReportTemplate template) {
        return toAjax(reportTemplateService.updateById(template));
    }

    /**
     * 删除报告模板
     */
    @RequiresPermissions("enterprise:reportTemplate:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer ids) {
        reportTemplateService.deleteById(ids);
        //删除对应的指标模板
//        List<Integer> reportTemplateIdList = reportTemplateService.selectTargetModelByReportTemplateId(ids);
//        targetModelMapper.deleteBatchIds(reportTemplateIdList.toArray(new Integer[]{reportTemplateIdList.size()}));
        return AjaxResult.success();
    }

    @RequestMapping("/getOrg")
    @ResponseBody
    public AjaxResult getOrg(@RequestParam("enterpriseId")  Integer enterpriseId) {
        EnterpriseOrgTree enterpriseOrgTree = new EnterpriseOrgTree();
        enterpriseOrgTree.setPlatformEnterpriseId(enterpriseId);
        return AjaxResult.success().put("orgList", enterpriseOrgTreeService.find(enterpriseOrgTree));
    }

    /**
     * 进入选择报告模板添加页面
     */
    @RequestMapping("/selectReportTemplate")
    public String selectReportTemplate() {
        return "mini/reportTemplateMini";
    }

    /**
     * 进入选择指标模板添加页面
     */
    @RequestMapping("/selectTargetTemplate")
    public String selectTargetTemplate() {
        return "mini/targetModelMini";
    }

    @RequestMapping("/getTemplate")
    @ResponseBody
    public AjaxResult getTemplate(@RequestParam("id") Integer id,@RequestParam("type") String type, ModelMap modelMap){
        String name = "";
        switch (type){
            case "00" :
                name = templateService.findOneById(id).getName();
                break;
            case "10" :
                name  = targetModelService.findOneById(id).getName();
                break;
            default:break;
        }

        modelMap.put("name", name);
        modelMap.put("id", id);

        return AjaxResult.success().put("modelMap", modelMap);
    }

}
