package com.suixingpay.hw.web.controller.enterprise;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.framework.util.ShiroUtils;
import com.suixingpay.hw.enterprise.domain.Enterprise;
import com.suixingpay.hw.enterprise.domain.EnterpriseUser;
import com.suixingpay.hw.enterprise.service.IEnterpriseService;
import com.suixingpay.hw.web.service.SaasRemoteService;
import com.suixingpay.hw.web.util.IdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Classname EnterpriseInput
 * @Description 企业录入
 * @Date 2019/4/10 11:07
 * @Created liuyan[liu_yan@suixingpay.com]
 */
@Controller
@RequestMapping("/enterprise")
public class EnterpriseManageController extends BaseController
{
    private String prefix = "enterprise";

    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private SaasRemoteService saasRemoteService;

    //加载前端页面地址
    @Value("${fangcloud.saas.url}")
    private String saasUrl;

    /**
     * 跳转到企业录入页面
     * @param modelMap
     * @return
     */
    @RequiresPermissions("enterprise:view")
    @GetMapping()
    public String Enterprise(Map modelMap)
    {
        // 获取所有企业名称
        List<Enterprise> enterpriseList = enterpriseService.selectEnterpriseList(new Enterprise());
        modelMap.put("enterpriseList", enterpriseList);
        return prefix + "/enterprise";
    }

    /**
     * 查询企业信息
     * @param enterprise
     * @return
     */
    @RequiresPermissions("enterprise:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Enterprise enterprise)
    {
        startPage();
        List<Enterprise> list = enterpriseService.selectEnterpriseList(enterprise);
        return getDataTable(list);
    }

    /**
     * 新增企业页面
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    /**
     * 跳回主页面
     */
    @GetMapping("/enterprise")
    public String enterprise()
    {
        return prefix + "/enterprise";
    }
    /**
     * 新增企业
     */
    @RequiresPermissions("enterprise:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult save(MultipartHttpServletRequest request)
    {
        Map<String,MultipartFile> logo = request.getFileMap();
        MultipartFile file = logo.get("logo");
        Enterprise enterprise = new Enterprise();
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String uscd = request.getParameter("uscd");

        String image = "";
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            image = encoder.encode(file.getBytes());
            } catch (IOException e) {
            e.printStackTrace();
        }
        Object enterpriseId = IdUtil.getManyId("t_enterprise",1).get(0);
        enterprise.setLogo(image);
        enterprise.setName(name);
        enterprise.setAddress(address);
        enterprise.setUscd(uscd);
        enterprise.setEnterpriseId((Integer) (enterpriseId));
        enterprise.setCreater((ShiroUtils.getUserId()).intValue());
        return toAjax(enterpriseService.insertEnterprise(enterprise)).put("enterpriseId",enterpriseId);
    }

    /**
     * 添加企业头像
     */
    @RequiresPermissions("enterprise:addlogo")
    @PostMapping("/addlogo")
    @ResponseBody
    public String savelogo(@RequestParam("logo") MultipartFile file)
    {
        System.err.println(file);
        String image = "";
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            image = encoder.encode(file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 添加微信图片
     */
    @RequiresPermissions("enterprise:addwxChatHead")
    @PostMapping("/addwxChatHead")
    @ResponseBody
    public String savewxChatHead(@RequestParam("wxChatHead") MultipartFile file)
    {
        System.err.println(file);
        String image = "";
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            image = encoder.encode(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    /**
     * 新增用户管理员页面
     */
    @GetMapping("/addUser")
    public String addUser()
    {
        return prefix + "/addUser";
    }

    /**
     * 新增企业用户管理员
     */
    @RequiresPermissions("enterprise:addUser")
    @PostMapping("/addUser")
    @ResponseBody
    public AjaxResult saveUser(MultipartHttpServletRequest request)
    {
        Map<String,MultipartFile> wxChatHead = request.getFileMap();
        MultipartFile file = wxChatHead.get("wxChatHead");
        String image = "";
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            image =  encoder.encode(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        Integer enterpriseId =  Integer.parseInt(request.getParameter("enterpriseId"));
        String name = request.getParameter("name");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("account");
        String jobNumber = request.getParameter("jobNumber");
        String wxOpenId = request.getParameter("wxOpenId");
        String qicq = request.getParameter("qicq");
        enterpriseUser.setUserId((Integer) (IdUtil.getManyId("t_enterprise_user",1)).get(0));
        enterpriseUser.setEnterpriseId(enterpriseId);
        enterpriseUser.setWxChatHead(image);
        enterpriseUser.setAccount(account);
        enterpriseUser.setEmail(email);
        enterpriseUser.setJobNumber(jobNumber);
        enterpriseUser.setName(name);
        enterpriseUser.setPassword(password);
        enterpriseUser.setPhone(phone);
        enterpriseUser.setQicq(qicq);
        enterpriseUser.setWxOpenId(wxOpenId);
        enterpriseUser.setState("00");
        enterpriseService.insertEnterpriseUser(enterpriseUser);
        Enterprise enterprise = new Enterprise();
        Integer userId = enterpriseUser.getUserId();
        enterprise.setAdmin(userId);
        enterprise.setEnterpriseId(enterpriseId);
        int insert = enterpriseService.updateEnterpriseInfo(enterprise);
        return toAjax(insert);

    }

    /**
     * 跳转修改企业页面
     */
    @GetMapping("/edit/{enterpriseId}")
    public String edit(@PathVariable("enterpriseId") Integer enterpriseId, ModelMap mmap)
    {
        mmap.put("enterprise", enterpriseService.selectEnterpriseByEnterpriseId(enterpriseId));
        return prefix + "/edit";
    }
    /**
     * 修改企业
     */
    @RequiresPermissions("enterprise:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MultipartHttpServletRequest request)
    {
        Map<String,MultipartFile> logo = request.getFileMap();
        MultipartFile file = logo.get("logo");
        Enterprise enterprise = new Enterprise();
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String uscd = request.getParameter("uscd");
        Integer enterpriseId = Integer.parseInt(request.getParameter("enterpriseId"));

        String image = "";
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            image = encoder.encode(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        enterprise.setLogo(image);
        enterprise.setName(name);
        enterprise.setAddress(address);
        enterprise.setUscd(uscd);
        enterprise.setCreater((ShiroUtils.getUserId()).intValue());
        enterprise.setEnterpriseId(enterpriseId);
        return toAjax(enterpriseService.updateEnterpriseInfo(enterprise));
    }

    /**
     * 删除企业
     */
    @RequiresPermissions("enterprise:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer id)
    {
            enterpriseService.deleteEnterpriseUserByEnterpriseId(id);
            return toAjax(enterpriseService.deleteEnterpriseByEnterpriseId(id));
    }

    @RequiresPermissions("enterprise:iframeEnterpriseUser")
    @RequestMapping("/iframeEnterpriseUser/{enterpriseId}")
    public String iframeEnterpriseUser(@PathVariable("enterpriseId") Integer enterpriseId, ModelMap modelMap, HttpServletResponse response) {
        modelMap.put("enterpriseId", enterpriseId);
        modelMap.put("saasUrl", saasUrl);
        modelMap.put("token", saasRemoteService.testLogin(response).getData().get("token"));
        return "/enterprise/enterpriseUser";
    }


}
