package com.suixingpay.hw.web.controller.platform;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.platform.domain.TargetModelContentTemplate;
import com.suixingpay.hw.platform.service.ITargetModelContentTemplateService;
import com.suixingpay.hw.platform.service.ITargetModelService;
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
 * @description: 平台指标模型展示内容模板 Controller
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-25 23:17
 **/
@Controller
@RequestMapping("/targetModelContentTemplate")
public class TargetModelContentTemplateController extends BaseController {
    @Autowired
    private ITargetModelContentTemplateService tmctService;

    @Autowired
    private ITargetModelService targetModelService;

    /**
     * 进入指标模板列表页面
     */
    @RequiresPermissions("targetModelContentTemplate:view")
    @RequestMapping("/view")
    public String view() {
        return "platform/targetModelContentTemplate";
    }

    /**
     * 获取指标模板列表
     */
    @RequiresPermissions("targetModelContentTemplate:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(TargetModelContentTemplate tmct) {
        startPage();
        List<TargetModelContentTemplate> tmctList = tmctService.find(tmct);
        return getDataTable(tmctList);
    }

    /**
     * 进入指标模板添加页面
     */
    @RequestMapping("/add")
    public String add() {
        return "platform/targetModelContentTemplateAdd";
    }

    /**
     * 新增指标模板
     */
    @RequiresPermissions("targetModelContentTemplate:save")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult addSave(TargetModelContentTemplate tmct) {

        tmct.setTargetModelTemplateId((Integer) IdUtil.getManyId("t_target_model_content_template",1).get(0));
        tmctService.add(tmct);

        return AjaxResult.success();
    }

    /**
     * 修改指标模板
     */
    @RequestMapping("/edit/{targetModelTemplateId}")
    public String edit(@PathVariable("targetModelTemplateId") Integer targetModelTemplateId, ModelMap mmap) {
        TargetModelContentTemplate tmct = tmctService.findOneById(targetModelTemplateId);
        mmap.put("targetModelContentTemplate", tmct);
        mmap.put("name", targetModelService.findOneById(tmct.getTargetModelId()).getName());
        return "platform/targetModelContentTemplateEdit";
    }

    /**
     * 修改保存指标模板
     */
    @RequiresPermissions("targetModelContentTemplate:edit")
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(TargetModelContentTemplate tmct) {
        return toAjax(tmctService.updateById(tmct));
    }

    /**
     * 删除指标模板
     */
    @RequiresPermissions("targetModelContentTemplate:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tmctService.deleteBatchIds(ids));
    }
}