package com.hq.web.controller.loggetter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hq.common.annotation.Log;
import com.hq.common.core.controller.BaseController;
import com.hq.common.core.domain.AjaxResult;
import com.hq.common.core.page.TableDataInfo;
import com.hq.common.enums.BusinessType;
import com.hq.framework.util.ShiroUtils;
import com.hq.loggetter.domain.Machine;
import com.hq.loggetter.service.IMachineService;


/**
 * 机器信息
 *   包括 服务组 和IP
 * 
 * 
 * @author huzj
 */
@Controller
@RequestMapping("/loggetter/machine")
public class GetterMachineController extends BaseController
{
    private String prefix = "machine";

    @Autowired
    private IMachineService machineService;

    @GetMapping()
    public String machine()
    {
        return prefix + "/machine";	
    }

    /**
     * 查询机器信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Machine machine)
    {
        startPage();
        List<Machine> list = machineService.selectMachineList(machine);
        return getDataTable(list);
    }

    /**
     * 新增机器信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存机器信息
     */
    @Log(title = "新增保存机器信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Machine machine)
    {
    	machine.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(machineService.insertMachine(machine));
    }

    /**
     * 修改机器信息,前后端绑定
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        mmap.put("machine", machineService.selectMachineById(id));
        return prefix + "/edit";
    }

    /**
     * 修改保存机器信息
     */
    @Log(title = "修改保存机器信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Machine machine)
    {
    	machine.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(machineService.updateMachine(machine));
    }

    /**
     * 删除机器
     */
    @Log(title = "删除机器信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(machineService.deleteMachineByIds(ids));
    }
}
