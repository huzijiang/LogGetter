package com.suixingpay.hw.web.controller.platform;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @description: 平台报告模型 Controller
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 15:21
 **/
@Controller
@RequestMapping("/reportTemplate")
public class ReportTemplateController extends BaseController {

    @Autowired
    private IReportTemplateService reportTemplateService;

    @Autowired
    private ITargetModelService targetModelService;

    /**
     * 进入平台报告模型列表页面
     */
    @RequiresPermissions("reportTemplate:view")
    @RequestMapping("/view")
    public String view() {
        return "platform/reportTemplate";
    }

    /**
     * 获取平台报告模型列表
     */
    @RequiresPermissions("reportTemplate:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReportTemplate template) {
        startPage();
        List<ReportTemplate> reportTemplateList = reportTemplateService.find(template);
        return getDataTable(reportTemplateList);
    }

    /**
     * 进入平台报告模型添加页面
     */
    @RequestMapping("/add")
    public String add() {
        return "platform/reportTemplateAdd";
    }

    /**
     * 新增平台报告模型
     */
    @RequiresPermissions("reportTemplate:save")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult addSave(ReportTemplate template) {
        template.setReportTemplateId((Integer) IdUtil.getManyId("t_report_template",1).get(0));
        template.setCreateDate(new Date());
        template.setCreater(ShiroUtils.getUserId().intValue());
        return toAjax(reportTemplateService.add(template));
    }

    /**
     * 修改平台报告模型
     */
    @RequestMapping("/edit/{reportTemplateId}")
    public String edit(@PathVariable("reportTemplateId") Integer reportTemplateId, ModelMap modelMap) {
        modelMap.put("reportTemplate", reportTemplateService.findOneById(reportTemplateId));
        return "platform/reportTemplateEdit";
    }

    /**
     * 修改保存平台报告模型
     */
    @RequiresPermissions("reportTemplate:edit")
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(ReportTemplate template) {
        return toAjax(reportTemplateService.updateByReportTemplateId(template));
    }

    /**
     * 删除平台报告模型
     *
     * @param ids 平台报告模型编号
     */
    @RequiresPermissions("reportTemplate:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer ids) {
        //删除该报告模型与报告指标关系表数据
        targetModelService.deleteReportTargetRelationByReportId(ids);
        //删除该报告模型
        reportTemplateService.deleteById(ids);
        return AjaxResult.success();
    }
}
