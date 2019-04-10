package com.suixingpay.hw.web.controller.system;

import com.suixingpay.hw.common.annotation.Log;
import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.common.enums.BusinessType;
import com.suixingpay.hw.common.utils.poi.ExcelUtil;
import com.suixingpay.hw.system.domain.Testtable;
import com.suixingpay.hw.system.service.ITesttableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试 信息操作处理
 * 
 * @author huzj
 * @date 2019-03-20
 */
@Controller
@RequestMapping("/system/testtable")
public class TesttableController extends BaseController
{
    private String prefix = "system/testtable";
	
	@Autowired
	private ITesttableService testtableService;
	
	@RequiresPermissions("system:testtable:view")
	@GetMapping()
	public String testtable()
	{
	    return prefix + "/testtable";
	}
	
	/**
	 * 查询测试列表
	 */
	@RequiresPermissions("system:testtable:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Testtable testtable)
	{
		startPage();
        List<Testtable> list = testtableService.selectTesttableList(testtable);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出测试列表
	 */
	@RequiresPermissions("system:testtable:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Testtable testtable)
    {
    	List<Testtable> list = testtableService.selectTesttableList(testtable);
        ExcelUtil<Testtable> util = new ExcelUtil<Testtable>(Testtable.class);
        return util.exportExcel(list, "testtable");
    }
	
	/**
	 * 新增测试
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存测试
	 */
	@RequiresPermissions("system:testtable:add")
	@Log(title = "测试", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Testtable testtable)
	{		
		return toAjax(testtableService.insertTesttable(testtable));
	}

	/**
	 * 修改测试
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Testtable testtable = testtableService.selectTesttableById(id);
		mmap.put("testtable", testtable);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存测试
	 */
	@RequiresPermissions("system:testtable:edit")
	@Log(title = "测试", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Testtable testtable)
	{		
		return toAjax(testtableService.updateTesttable(testtable));
	}
	
	/**
	 * 删除测试
	 */
	@RequiresPermissions("system:testtable:remove")
	@Log(title = "测试", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(testtableService.deleteTesttableByIds(ids));
	}
	
}
