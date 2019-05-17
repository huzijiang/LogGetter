package com.suixingpay.hw.web.controller.report;

import com.suixingpay.hw.common.core.controller.BaseController;
import com.suixingpay.hw.common.core.domain.AjaxResult;
import com.suixingpay.hw.common.core.page.TableDataInfo;
import com.suixingpay.hw.common.utils.StringUtils;
import com.suixingpay.hw.enterprise.domain.EnterpriseReportTemplate;
import com.suixingpay.hw.enterprise.domain.EnterpriseUser;
import com.suixingpay.hw.enterprise.service.IEnterpriseReportTemplateService;
import com.suixingpay.hw.enterprise.service.IEnterpriseUserService;
import com.suixingpay.hw.framework.util.ShiroUtils;
import com.suixingpay.hw.report.domain.EntUserReceiveReportRelation;
import com.suixingpay.hw.report.service.IEntUserReceiveReportRelationService;
import com.suixingpay.hw.web.util.IdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 企业用户邮箱接收 报告信息关系 controller
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-16 11:25
 **/
@Controller
@RequestMapping("/report/mail")
public class EntUserReceiveReportRelationController extends BaseController {

    @Autowired
    private IEntUserReceiveReportRelationService entUser3RService;

    @Autowired
    private IEnterpriseUserService enterpriseUserService;

    @Autowired
    private IEnterpriseReportTemplateService entReportTempService;

    /**
     * 进入：企业用户邮箱接收 报告信息关系页面
     */
    @RequiresPermissions("report:mail:view")
    @RequestMapping("/view")
    public String view() {
        return "report/mail/entUser3R";
    }

    /**
     * 获取：企业用户邮箱接收 报告信息关系列表
     */
    @RequiresPermissions("report:mail:list")
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(EntUserReceiveReportRelation entUser3R) {
        startPage();
        List<EntUserReceiveReportRelation> entUser3RList = entUser3RService.find(entUser3R);
        return getDataTable(entUser3RList);
    }

    @RequiresPermissions("report:mail:detail")
    @RequestMapping("/detail/{entReportTempId}")
    public String detail(@PathVariable("entReportTempId") Integer entReportTempId, ModelMap modelMap) {
        modelMap.put("entReportTemp", entReportTempService.findOneById(entReportTempId));
        modelMap.put("entUser3RList", entUser3RService.findByEntReportTempId(entReportTempId));
        return "report/mail/entUser3RDetail";
    }

    @RequestMapping("/getEntUser3RList/{entReportTempId}")
    @ResponseBody
    public TableDataInfo getEntUser3RList(@PathVariable("entReportTempId") Integer entReportTempId) {
        logger.info("数据 = [{}]", entReportTempId);
        startPage();
        List<EntUserReceiveReportRelation> entUser3RList = entUser3RService.findByEntReportTempId(entReportTempId);
        return getDataTable(entUser3RList);
    }

    @RequiresPermissions("report:mail:edit")
    @RequestMapping("/edit/{entReportTempId}")
    public String edit(@PathVariable("entReportTempId") Integer entReportTempId, ModelMap modelMap) {
        EnterpriseReportTemplate entReportTemp = entReportTempService.findOneById(entReportTempId);

        modelMap.put("entUserList", enterpriseUserService.findByEnterpriseId(entReportTemp.getEnterpriseId()));
        modelMap.put("entReportTemp", entReportTemp);
        modelMap.put("entUser3RList", entUser3RService.findByEntReportTempId(entReportTempId));
        return "report/mail/entUser3REdit";
    }

    @RequestMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestParam("userIds") String userIds
            ,@RequestParam("enterpriseReportTemplateId") Integer entReportTempId
            ,@RequestParam("userType") String userType) {
        try {
            //临时的邮件
            if (!StringUtils.isEmpty(userIds)) {
                //内部人员
                List<Integer> inUserIdList = new ArrayList<>();
                //外部人员邮箱
                List<String> outUserEmailList = new ArrayList<>();
                String[] strArr = userIds.split(",");
                for (String s : strArr) {
                    //判断字符串中是否含有@
                    if (s.matches(".*@.*")) {
                        outUserEmailList.add(s);
                    } else {
                        inUserIdList.add(Integer.parseInt(s));
                    }
                }

                //入库数据
                List<EntUserReceiveReportRelation> resultList = new ArrayList<>();

                //筛选入库的内部人员ID
                List<Integer> inUserIdList2DB = new ArrayList<>();
                //筛选入库的外部人员邮箱
                List<String> outUserEmailList2DB = new ArrayList<>();

                //内部人员
                for (Integer entUserId : inUserIdList) {
                    //去除 已经与该报告创建了联系的 （收件人/抄送人）用户ID
                    EntUserReceiveReportRelation entUser3R = new EntUserReceiveReportRelation();
                    entUser3R.setEnterpriseUserId(entUserId);
                    entUser3R.setEnterpriseReportTemplateId(entReportTempId);
                    entUser3R.setUserType(userType);
                    if (entUser3RService.find(entUser3R).size() == 0) {
                        inUserIdList2DB.add(entUserId);
                    }
                }

                inUserIdList2DB.forEach(entUserId -> {
                    EnterpriseUser entUser = enterpriseUserService.findByUserId(entUserId);
                    EntUserReceiveReportRelation entUser3R = new EntUserReceiveReportRelation();
                    entUser3R.setId((Integer) IdUtil.getManyId("t_et_user_receive_report_relation",1).get(0));
                    entUser3R.setEnterpriseId(entUser.getEnterpriseId());
                    entUser3R.setEnterpriseReportTemplateId(entReportTempId);
                    entUser3R.setEnterpriseUserId(entUserId);
                    entUser3R.setEmail(entUser.getAccount()); // 账号就是邮箱
                    entUser3R.setCreater(ShiroUtils.getUserId().intValue());
                    entUser3R.setCreateDate(new Date());
                    entUser3R.setUserType(userType);
                    resultList.add(entUser3R);
                });

                //外部人员邮箱
                for (String outUserEmail : outUserEmailList) {
                    //去除 已经与该报告创建了联系的 （收件人/抄送人）用户ID
                    EntUserReceiveReportRelation entUser3R = new EntUserReceiveReportRelation();
                    entUser3R.setEmail(outUserEmail);
                    entUser3R.setEnterpriseReportTemplateId(entReportTempId);
                    entUser3R.setUserType(userType);
                    if (entUser3RService.find(entUser3R).size() == 0) {
                        outUserEmailList2DB.add(outUserEmail);
                    }
                }

                outUserEmailList2DB.forEach(outUserEmail -> {
                    EntUserReceiveReportRelation entUser3R = new EntUserReceiveReportRelation();
                    entUser3R.setId((Integer) IdUtil.getManyId("t_et_user_receive_report_relation",1).get(0));
                    entUser3R.setEnterpriseReportTemplateId(entReportTempId);
                    entUser3R.setCreater(ShiroUtils.getUserId().intValue());
                    entUser3R.setEmail(outUserEmail);
                    entUser3R.setCreateDate(new Date());
                    entUser3R.setUserType(userType);
                    resultList.add(entUser3R);
                });
                entUser3RService.batchInsert(resultList);
            }
        } catch (Exception e) {
            logger.error("添加邮件人出错：[{}]", e.getMessage());
            return AjaxResult.error("添加出错，请联系管理员处理！");
        }

        return AjaxResult.success();
    }

    @RequestMapping("/getEntReportTemp")
    @ResponseBody
    public AjaxResult getEntReportTemp(@RequestParam("enterpriseId") Integer enterpriseId, @RequestParam("makeCycle") String makeCycle) {
        EnterpriseReportTemplate template = new EnterpriseReportTemplate();
        template.setEnterpriseId(enterpriseId);
        template.setMakeCycle(makeCycle);
        return AjaxResult.success().put("entReportTempList", entReportTempService.find(template));
    }

    @RequiresPermissions("enterprise:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try {
            return toAjax(entUser3RService.deleteById(Integer.parseInt(ids)));
        } catch (Exception e) {
            logger.error("删除邮件人出错：[{}]", e.getMessage());
            return error(e.getMessage());
        }
    }

}
