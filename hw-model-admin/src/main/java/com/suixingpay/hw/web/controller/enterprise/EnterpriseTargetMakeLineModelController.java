package com.suixingpay.hw.web.controller.enterprise;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.enterprise.domain.Enterprise;
import com.suixingpay.hw.enterprise.domain.EnterpriseReportTemplate;
import com.suixingpay.hw.enterprise.domain.EnterpriseTargetMakeLineModel;
import com.suixingpay.hw.enterprise.domain.EnterpriseTargetTemplate;
import com.suixingpay.hw.enterprise.service.IEnterpriseReportTemplateService;
import com.suixingpay.hw.enterprise.service.IEnterpriseService;
import com.suixingpay.hw.enterprise.service.IEnterpriseTargetMakeLineModelService;
import com.suixingpay.hw.enterprise.service.IEnterpriseTargetTemplateService;
import com.suixingpay.hw.framework.util.ShiroUtils;
import com.suixingpay.hw.platform.domain.TargetMakeLineModel;
import com.suixingpay.hw.platform.domain.TargetModelContentTemplate;
import com.suixingpay.hw.platform.service.IReportTemplateService;
import com.suixingpay.hw.platform.service.ITargetMakeLineModelService;
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

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 23:53
 **/
@Controller
@RequestMapping("/enterprise/targetMakeLine")
public class EnterpriseTargetMakeLineModelController extends BaseController {

    @Autowired
    private IEnterpriseTargetMakeLineModelService entTargetMakeLineModelService;

    @Autowired
    private IEnterpriseReportTemplateService entReportTempService;

    @Autowired
    private IReportTemplateService reportTemplateService;

    @Autowired
    private ITargetModelService targetModelService;

    @Autowired
    private ITargetModelContentTemplateService tmctService;

    @Autowired
    private ITargetMakeLineModelService targetMakeLineModelService;

    /**
     * 进入指标模板列表页面
     */
    @RequiresPermissions("enterprise:targetMakeLine:view")
    @RequestMapping("/view")
    public String view(ModelMap modelMap) {
        return "enterprise/targetmakeline/enterpriseTargetMakeLineModel";
    }

    /**
     * 获取指标模板列表
     */
    @RequiresPermissions("enterprise:targetMakeLine:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(EnterpriseTargetMakeLineModel targetMakeLineModel) {
        startPage();
        List<EnterpriseTargetMakeLineModel> reportTemplateList = entTargetMakeLineModelService.find(targetMakeLineModel);
        return getDataTable(reportTemplateList);
    }

    /**
     * 进入指标模板添加页面
     */
    @RequestMapping("/add/{enterpriseId}/{entReportTempId}/{entTargetTempId}")
    public String add(@PathVariable("enterpriseId") Integer enterpriseId, @PathVariable("entReportTempId") Integer entReportTempId, @PathVariable("entTargetTempId") Integer entTargetTempId, ModelMap modelMap){

        EnterpriseReportTemplate entReportTemp = entReportTempService.findOneById(entReportTempId);
        List<Integer> targetModelIds = reportTemplateService
                .selectTargetModelByReportTemplateId(entReportTemp.getReportTemplateId());

        modelMap.put("enterpriseId", enterpriseId);
        modelMap.put("enterpriseReportId", entReportTempId);
        modelMap.put("enterpriseTargetTemplateId", entTargetTempId);
        modelMap.put("targetModelList", targetModelService.selectByIds(targetModelIds));

        return "enterprise/targetmakeline/enterpriseTargetMakeLineModelAdd";
    }

    /**
     * 新增指标模板
     */
    @RequiresPermissions("enterprise:targetMakeLine:save")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult addSave(EnterpriseTargetMakeLineModel targetMakeLineModel) {
        targetMakeLineModel.setEnterpriseTargetMakeLineModelId((Integer) IdUtil.getManyId("t_enterprise_target_make_line_model",1).get(0));
        targetMakeLineModel.setLastChangeDate(new Date());
        targetMakeLineModel.setLastChanger((ShiroUtils.getUserId()).intValue());
        entTargetMakeLineModelService.add(targetMakeLineModel);
        return AjaxResult.success();
    }

    /**
     * 修改指标模板
     */
    @RequestMapping("/edit/{enterpriseTargetMakeLineModelId}")
    public String edit(@PathVariable("enterpriseTargetMakeLineModelId") Integer enterpriseTargetMakeLineModelId, ModelMap mmap) {
        EnterpriseTargetMakeLineModel entTargetMakeLineModel = entTargetMakeLineModelService.findOneById(enterpriseTargetMakeLineModelId);

        EnterpriseReportTemplate entReportTemp = entReportTempService.findOneById(entTargetMakeLineModel.getEnterpriseReportId());
        List<Integer> targetModelIds = reportTemplateService
                .selectTargetModelByReportTemplateId(entReportTemp.getReportTemplateId());

        mmap.put("targetModelList", targetModelService.selectByIds(targetModelIds));
        mmap.put("targetModelTempList", tmctService.selectByTargetModelId(entTargetMakeLineModel.getTargetModelId()));
        mmap.put("makeLineList", targetMakeLineModelService.selectByTargetModelTempId(entTargetMakeLineModel.getTargetModelTemplateId()));
        mmap.put("enterpriseTargetMakeLineModel", entTargetMakeLineModel);
        return "enterprise/targetmakeline/enterpriseTargetMakeLineModelEdit";
    }

    /**
     * 修改保存指标模板
     */
    @RequiresPermissions("enterprise:targetMakeLine:edit")
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(EnterpriseTargetMakeLineModel targetMakeLineModel) {
        targetMakeLineModel.setLastChangeDate(new Date());
        targetMakeLineModel.setLastChanger(ShiroUtils.getUserId().intValue());
        return toAjax(entTargetMakeLineModelService.updateById(targetMakeLineModel));
    }

    /**
     * 删除指标模板
     */
    @RequiresPermissions("enterprise:targetMakeLine:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(entTargetMakeLineModelService.deleteBatchIds(ids));
    }

}
