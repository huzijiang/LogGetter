package com.suixingpay.hw.web.controller.enterprise;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.enterprise.domain.EnterpriseReportTemplate;
import com.suixingpay.hw.enterprise.service.IEnterpriseReportTemplateService;
import com.suixingpay.hw.framework.util.ShiroUtils;
import com.suixingpay.hw.platform.service.IReportTemplateService;
import com.suixingpay.hw.web.util.IdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String add() {
        return "enterprise/report/enterpriseReportTemplateAdd";
    }

    /**
     * 新增报告模板
     */
    @RequiresPermissions("enterprise:reportTemplate:save")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult addSave(EnterpriseReportTemplate template) {
        template.setReportTemplateId((Integer) IdUtil.getManyId("t_enterprise_report_template",1).get(0));
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
}
