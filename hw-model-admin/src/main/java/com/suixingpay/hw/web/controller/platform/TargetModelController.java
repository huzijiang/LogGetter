package com.suixingpay.hw.web.controller.platform;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.framework.util.ShiroUtils;
import com.suixingpay.hw.platform.domain.TargetModel;
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
 * @description: 指标模板 Controller
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 15:22
 **/
@Controller
@RequestMapping("/targetModel")
public class TargetModelController extends BaseController {

    @Autowired
    private ITargetModelService targetModelService;

    /**
     * 进入指标模板列表页面
     */
    @RequiresPermissions("targetModel:view")
    @RequestMapping("/view")
    public String view() {
        return "platform/targetModel";
    }

    /**
     * 获取指标模板列表
     */
    @RequiresPermissions("targetModel:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(TargetModel targetModel) {
        startPage();
        List<TargetModel> reportTemplateList = targetModelService.find(targetModel);
        return getDataTable(reportTemplateList);
    }

    /**
     * 进入指标模板添加页面
     */
    @RequestMapping("/add")
    public String add() {
        return "platform/targetModelAdd";
    }

    /**
     * 新增指标模板
     */
    @RequiresPermissions("targetModel:save")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult addSave(TargetModel targetModel) {
        targetModel.setTargetModelId((Integer) IdUtil.getManyId("t_target_model",1).get(0));
        targetModel.setCreateDate(new Date());
        targetModel.setCreater(ShiroUtils.getUserId().intValue());
        targetModelService.add(targetModel);

        //targetModelService.addReportTargetRelation(targetModel.getReportTemplateId(), targetModel.getTargetModelId());

        return AjaxResult.success();
    }

    /**
     * 修改指标模板 TODO 若为空 处理
     */
    @RequestMapping("/edit/{targetModelId}")
    public String edit(@PathVariable("targetModelId") Integer targetModelId, ModelMap mmap) {
        TargetModel targetModel = targetModelService.findOneById(targetModelId);
        mmap.put("targetModel", targetModel);
        mmap.put("name", targetModelService.findOneById(targetModel.getTargetModelId()).getName());
        return "platform/targetModelEdit";
    }

    /**
     * 修改保存指标模板
     */
    @RequiresPermissions("targetModel:edit")
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(TargetModel targetModel) {
        return toAjax(targetModelService.updateById(targetModel));
    }

    /**
     * 删除指标模板
     */
    @RequiresPermissions("targetModel:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        targetModelService.deleteRelationIds(ids);
        return toAjax(targetModelService.deleteBatchIds(ids));
    }
}
