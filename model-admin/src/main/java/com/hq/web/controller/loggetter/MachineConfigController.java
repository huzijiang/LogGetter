package com.hq.web.controller.loggetter;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hq.common.core.controller.BaseController;
import com.hq.common.core.page.TableDataInfo;
import com.hq.loggetter.domain.Machine;
import com.hq.loggetter.service.IMachineService;
import com.hq.system.domain.SysNotice;

/**
 * 
 * 
 * 机器映射 控制器
 * 
 * @author huzj
 *
 */

@Controller
@RequestMapping("/loggetter/machine")
public class MachineConfigController  extends BaseController{

	private String prefix = "machine";
	
	private IMachineService machineService;
	
	

	@RequiresPermissions("loggetter:machine:view")
	@GetMapping()
	public String notice() {
		return prefix + "/machine";
	}
	
    /**
     * 查询公告列表
     */
    @RequiresPermissions("loggetter:machine:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Machine machine){
    	

        startPage();
        Machine machinea=new  Machine();
		    	machinea.setId(0);
		    	machinea.setGroupName("");
		    	machinea.setIp("");
        List<Machine> list = machineService.selectMachines(machinea);
        
        return getDataTable(list);
    }

}
