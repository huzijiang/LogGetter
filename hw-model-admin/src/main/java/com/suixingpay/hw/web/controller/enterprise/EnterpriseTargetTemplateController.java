package com.suixingpay.hw.web.controller.enterprise;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.enterprise.domain.EnterpriseTargetTemplate;
import com.suixingpay.hw.enterprise.service.IEnterpriseTargetTemplateService;
import com.suixingpay.hw.web.util.IdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:53
 **/
@Controller
@RequestMapping("/enterprise/targetTemplate")
public class EnterpriseTargetTemplateController extends BaseController {

    @Autowired
    private IEnterpriseTargetTemplateService targetTemplateService;

    /**
     * 进入企业指标模板列表页面
     */
    @RequiresPermissions("enterprise:targetTemplate:view")
    @RequestMapping("/view")
    public String view() {
        return "enterprise/report/enterpriseTargetTemplate";
    }

    /**
     * 获取企业指标模板列表
     */
    @RequiresPermissions("enterprise:targetTemplate:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(EnterpriseTargetTemplate template) {
        startPage();
        List<EnterpriseTargetTemplate> targetTemplateList = targetTemplateService.find(template);
        return getDataTable(targetTemplateList);
    }

    /**
     * 进入企业指标模板添加页面
     */
    @RequestMapping("/add")
    public String add() {
        return "enterprise/report/enterpriseTargetTemplateAdd";
    }

    /**
     * 新增企业指标模板
     */
    @RequiresPermissions("enterprise:targetTemplate:save")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult addSave(EnterpriseTargetTemplate template) {
        template.setEnterpriseTargetTemplateId((Integer) IdUtil.getManyId("t_enterprise_target_template",1).get(0));
        return toAjax(targetTemplateService.add(template));
    }

    /**
     * 进入修改企业指标模板页面
     */
    @RequestMapping("/edit/{targetTemplateId}")
    public String edit(@PathVariable("targetTemplateId") Integer targetTemplateId, ModelMap mmap) {
        mmap.put("targetTemplate", targetTemplateService.findOneById(targetTemplateId));
        return "enterprise/report/enterpriseTargetTemplateEdit";
    }

    /**
     * 修改保存企业指标模板
     */
    @RequiresPermissions("enterprise:targetTemplate:edit")
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(EnterpriseTargetTemplate template) {
        return toAjax(targetTemplateService.updateById(template));
    }

    /**
     * 删除企业指标模板
     */
    @RequiresPermissions("enterprise:targetTemplate:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer ids) {
        targetTemplateService.deleteById(ids);
        return AjaxResult.success();
    }
}
