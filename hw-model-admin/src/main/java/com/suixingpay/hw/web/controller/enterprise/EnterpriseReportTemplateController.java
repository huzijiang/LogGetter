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
    private IEnterpriseReportTemplateService entReportTemplateService;

    @Autowired
    private IReportTemplateService reportTemplateService;

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
        List<EnterpriseReportTemplate> reportTemplateList = entReportTemplateService.find(template);
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
        ReportTemplate reportTemplate = reportTemplateService.findOneById(template.getReportTemplateId());
        template.setMakeCycle(reportTemplate.getMakeCycle());
        template.setEnterpriseReportTemplateId((Integer) IdUtil.getManyId("t_enterprise_report_template",1).get(0));
        template.setCreateDate(new Date());
        template.setCreater(ShiroUtils.getUserId().intValue());
        return toAjax(entReportTemplateService.add(template));
    }

    /**
     * 修改报告模板
     */
    @RequestMapping("/edit/{enterpriseReportTemplateId}")
    public String edit(@PathVariable("enterpriseReportTemplateId") Integer enterpriseReportTemplateId, ModelMap mmap) {
        EnterpriseReportTemplate enterpriseReportTemplate = entReportTemplateService.findOneById(enterpriseReportTemplateId);
        ReportTemplate reportTemplate = reportTemplateService.findOneById(enterpriseReportTemplate.getReportTemplateId());
        mmap.put("enterpriseReportTemplate", enterpriseReportTemplate);
        mmap.put("name", reportTemplate.getName());
//        mmap.put("id", reportTemplate);

        List<Enterprise> enterpriseList = enterpriseService.selectEnterpriseList(new Enterprise());
        mmap.put("enterpriseList", enterpriseList);

        EnterpriseOrgTree enterpriseOrgTree = new EnterpriseOrgTree();
        enterpriseOrgTree.setPlatformEnterpriseId(enterpriseReportTemplate.getEnterpriseId());
        mmap.put("orgList", enterpriseOrgTreeService.find(enterpriseOrgTree));

        return "enterprise/report/enterpriseReportTemplateEdit";
    }

    /**
     * 修改保存报告模板
     */
    @RequiresPermissions("enterprise:reportTemplate:edit")
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(EnterpriseReportTemplate template) {
        ReportTemplate reportTemplate = reportTemplateService.findOneById(template.getReportTemplateId());
        template.setMakeCycle(reportTemplate.getMakeCycle());
        return toAjax(entReportTemplateService.updateById(template));
    }

    /**
     * 删除报告模板
     */
    @RequiresPermissions("enterprise:reportTemplate:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer ids) {
        entReportTemplateService.deleteById(ids);
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
}
