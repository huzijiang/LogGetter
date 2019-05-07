package com.suixingpay.hw.web.controller.platform;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.platform.domain.TargetMakeLineModel;
import com.suixingpay.hw.platform.domain.TargetModelContentTemplate;
import com.suixingpay.hw.platform.service.ITargetMakeLineModelService;
import com.suixingpay.hw.platform.service.ITargetModelContentTemplateService;
import com.suixingpay.hw.web.util.IdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description: 指标模板 Controller
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 15:22
 **/
@Controller
@RequestMapping("/targetMakeLineModel")
public class TargetMakeLineModelController extends BaseController {

    @Autowired
    private ITargetMakeLineModelService targetMakeLineModelService;

    @Autowired
    private ITargetModelContentTemplateService tmctService;

    /**
     * 进入指标模板列表页面
     */
    @RequiresPermissions("targetMakeLineModel:view")
    @RequestMapping("/view")
    public String view() {
        return "platform/targetMakeLineModel";
    }

    /**
     * 获取指标模板列表
     */
    @RequiresPermissions("targetMakeLineModel:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(TargetMakeLineModel targetMakeLineModel) {
        startPage();
        List<TargetMakeLineModel> reportTemplateList = targetMakeLineModelService.find(targetMakeLineModel);
        return getDataTable(reportTemplateList);
    }

    /**
     * 进入指标模板添加页面
     */
    @RequestMapping("/add")
    public String add(){
        return "platform/targetMakeLineModelAdd";
    }

    /**
     * 新增指标模板
     */
    @RequiresPermissions("targetMakeLineModel:save")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult addSave(TargetMakeLineModel targetMakeLineModel) {
        targetMakeLineModel.setTargetMakeLineModelId((IdUtil.getManyId("t_target_make_line_model",1).get(0)).toString());
        targetMakeLineModelService.add(targetMakeLineModel);
        return AjaxResult.success();
    }

    /**
     * 修改指标模板
     */
    @RequestMapping("/edit/{targetMakeLineModelId}")
    public String edit(@PathVariable("targetMakeLineModelId") Integer targetMakeLineModelId, ModelMap mmap) {
        TargetMakeLineModel targetMakeLineModel = targetMakeLineModelService.findOneById(targetMakeLineModelId);
        TargetModelContentTemplate tmct = tmctService.findOneById(targetMakeLineModel.getTargetModelTemplateId());
        mmap.put("targetMakeLineModel", targetMakeLineModel);
        mmap.put("name",tmct.getName());
        mmap.put("targetModelId", tmct.getTargetModelId());
        return "platform/targetMakeLineModelEdit";
    }

    /**
     * 修改保存指标模板
     */
    @RequiresPermissions("targetMakeLineModel:edit")
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(TargetMakeLineModel targetMakeLineModel) {
        return toAjax(targetMakeLineModelService.updateById(targetMakeLineModel));
    }

    /**
     * 删除指标模板
     */
    @RequiresPermissions("targetMakeLineModel:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        //todo 删除企业标记线表里相关联的数据
        return toAjax(targetMakeLineModelService.deleteBatchIds(ids));
    }

    @RequestMapping("/findOneById")
    @ResponseBody
    public AjaxResult  findOneById(@RequestParam("targetMakeLineModelId") Integer targetMakeLineModelId) {
        return AjaxResult.success().put("targetMakeLineModel", targetMakeLineModelService.findOneById(targetMakeLineModelId));
    }
}
