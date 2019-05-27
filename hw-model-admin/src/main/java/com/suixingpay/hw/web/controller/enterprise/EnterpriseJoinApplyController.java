package com.suixingpay.hw.web.controller.enterprise;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.enterprise.domain.EnterpriseJoinApply;
import com.suixingpay.hw.enterprise.service.IEnterpriseJoinApplyService;
import com.suixingpay.hw.framework.util.ShiroUtils;
import com.suixingpay.hw.web.util.IdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-27 19:19
 **/
@Controller
@RequestMapping("/enterprise/join/")
public class EnterpriseJoinApplyController extends BaseController {

    @Autowired
    private IEnterpriseJoinApplyService enterpriseJoinApplyService;

    @RequiresPermissions("enterprise:join:view")
    @RequestMapping("view")
    public String view() {
        return "enterprise/enterpriseJoinApply";
    }

    @RequiresPermissions("enterprise:join:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EnterpriseJoinApply enterpriseJoinApply) {
        startPage();
        List<EnterpriseJoinApply> list = enterpriseJoinApplyService.find(enterpriseJoinApply);
        return getDataTable(list);
    }

    @RequestMapping("/add")
    public String add() {
        return "enterprise/enterpriseJoinApplyAdd";
    }

    @RequiresPermissions("enterprise:join:save")
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult addSave(EnterpriseJoinApply enterpriseJoinApply) {
        enterpriseJoinApply.setEnterpriseJoinApplyId((Integer) IdUtil.getManyId("t_enterprise_report_template",1).get(0));
        enterpriseJoinApply.setApplyDate(new Date());
        enterpriseJoinApply.setAcceptor(ShiroUtils.getUserId().intValue());
        return toAjax(enterpriseJoinApplyService.add(enterpriseJoinApply));
    }

    @RequestMapping("/edit/{enterpriseJoinApplyId}")
    public String edit(@PathVariable("enterpriseJoinApplyId") Integer enterpriseJoinApplyId, ModelMap modelMap) {
        modelMap.put("enterpriseJoinApply", enterpriseJoinApplyService.findOneById(enterpriseJoinApplyId));
        return "enterprise/enterpriseJoinApplyEdit";
    }

    @RequiresPermissions("enterprise:join:edit")
    @PostMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(EnterpriseJoinApply enterpriseJoinApply) {
        return toAjax(enterpriseJoinApplyService.updateById(enterpriseJoinApply));
    }

    @RequiresPermissions("enterprise:join:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer ids) {
        enterpriseJoinApplyService.deleteById(ids);
        return AjaxResult.success();
    }
}
