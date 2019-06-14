package com.suixingpay.hw.framework.web.service;

import com.suixingpay.hw.common.utils.StringUtils;
import com.suixingpay.hw.enterprise.domain.*;
import com.suixingpay.hw.enterprise.service.*;
import com.suixingpay.hw.platform.domain.TargetWarehouse;
import com.suixingpay.hw.platform.mapper.TargetWarehouseMapper;
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

    @Autowired
    private IEnterpriseTargetTemplateService entTargetTempService;

    @Autowired
    private IEnterpriseTargetMakeLineModelService entTMLModelService;

    @Autowired
    private TargetWarehouseMapper targetWarehouseMapper;

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

    /**
     * 获取所有 企业指标模板
     * @return List<EnterpriseTargetTemplate>
     */
    public List<EnterpriseTargetTemplate> getEntTargetTempList() {
        return entTargetTempService.findAll();
    }

    /**
     * 获取所有 企业指标标记线模板
     * @return List<EnterpriseTargetMakeLineModel>
     */
    public List<EnterpriseTargetMakeLineModel> getEntMakeLineList() {
        return entTMLModelService.findAll();
    }


    public List<TargetWarehouse> getAllTargetWarehouse() {
        return targetWarehouseMapper.findAll();
    }
}
