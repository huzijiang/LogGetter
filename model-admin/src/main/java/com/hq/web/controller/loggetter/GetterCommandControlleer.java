package com.hq.web.controller.loggetter;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 命令 执行与记录 控制器
 * @author huzj
 *
 */

@Controller
@RequestMapping("/loggetter/command")
public class GetterCommandControlleer {
	
	 private String prefix = "command";
	
    @RequiresPermissions("system:notice:view")
    @GetMapping()
    public String notice()
    {
        return prefix + "/command";
    }
    
    
    
    

}
