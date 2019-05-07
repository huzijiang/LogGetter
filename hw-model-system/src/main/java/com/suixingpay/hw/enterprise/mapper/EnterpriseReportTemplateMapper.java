package com.suixingpay.hw.enterprise.mapper;

import com.suixingpay.hw.enterprise.domain.EnterpriseReportTemplate;

import java.util.List;

public interface EnterpriseReportTemplateMapper {

    /**
     * 查询（根据实体查询）
     *
     * @param template 企业报告模板
     * @return 企业报告模板列表
     */
    List<EnterpriseReportTemplate> find(EnterpriseReportTemplate template);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param template 企业报告模板
     * @return 变更数
     */
    int add(EnterpriseReportTemplate template);

    /**
     * 修改
     *
     * @param template 企业报告模板
     * @return 变更数
     */
    int updateById(EnterpriseReportTemplate template);

    /**
     * 查询（根据企业报告模板编号查询）
     *
     * @param reportTemplateId 企业报告模板编号
     * @return ReportTemplate
     */
    EnterpriseReportTemplate findOneById(Integer reportTemplateId);

    /**
     * 删除：根据企业报告模板编号删除
     *
     * @param reportTemplateId 企业报告模板编号
     * @return 变更数
     */
    int deleteById(Integer reportTemplateId);

    /**
     * 获取所有 企业报告模板
     *
     * @return List<EnterpriseReportTemplate>
     */
    List<EnterpriseReportTemplate> findAll();
}
