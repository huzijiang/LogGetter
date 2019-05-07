package com.suixingpay.hw.framework.web.service;

import com.suixingpay.hw.common.utils.StringUtils;
import com.suixingpay.hw.enterprise.domain.Enterprise;
import com.suixingpay.hw.enterprise.domain.EnterpriseOrgTree;
import com.suixingpay.hw.enterprise.domain.EnterpriseReportTemplate;
import com.suixingpay.hw.enterprise.service.IEnterpriseOrgTreeService;
import com.suixingpay.hw.enterprise.service.IEnterpriseReportTemplateService;
import com.suixingpay.hw.enterprise.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 与前台交互：平台维护相关功能 Service
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-06 09:38
 **/
@Service("platformDict")
public class PlatformCommonService {

    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private IEnterpriseOrgTreeService entOrgTreeService;

    @Autowired
    private IEnterpriseReportTemplateService entReportTempService;

    /**
     * 获取所有企业
     * @return List<Enterprise>
     */
    public List<Enterprise> getEnterpriseList() {
        return enterpriseService.findAll();
    }

    /**
     * 获取某个企业下所有部门，包括该企业自身
     * @return List<EnterpriseOrgTree>
     */
    public List<EnterpriseOrgTree> getEntOrgList(String platformEntId) {
        return entOrgTreeService.findByPlatformEntId(StringUtils.isEmpty(platformEntId) ? null : Integer.parseInt(platformEntId));
    }

    /**
     * 获取所有 企业报告模板
     * @return List<EnterpriseReportTemplate>
     */
    public List<EnterpriseReportTemplate> getEntReportTempList() {
        return entReportTempService.find(new EnterpriseReportTemplate());
    }
}
