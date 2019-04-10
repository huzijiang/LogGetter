package com.suixingpay.hw.web.controller.report;

import com.suixingpay.hw.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.TreeMap;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-08 15:31
 **/
@Controller
@RequestMapping("/report")
public class ReportManageController extends BaseController {

    @RequiresPermissions("report:list")
    @RequestMapping("/list")
    public String list(ModelMap mmap)
    {
        Map<String, Integer> map = new TreeMap<>();
        // "衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"
        map.put("衬衫", 5);
        map.put("羊毛衫", 20);
        map.put("雪纺衫", 36);
        map.put("裤子", 15);
        map.put("高跟鞋", 20);
        map.put("袜子", 49);
        mmap.addAttribute("myData", map);

        return "report/reportList";
    }
}
