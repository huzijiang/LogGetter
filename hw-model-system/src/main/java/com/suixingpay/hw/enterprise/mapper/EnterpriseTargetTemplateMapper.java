package com.suixingpay.hw.enterprise.mapper;

import com.suixingpay.hw.enterprise.domain.EnterpriseTargetTemplate;

import java.util.List;

public interface EnterpriseTargetTemplateMapper {

    /**
     * 查询（根据实体查询）
     *
     * @param template 企业指标模板
     * @return 企业指标模板列表
     */
    List<EnterpriseTargetTemplate> find(EnterpriseTargetTemplate template);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param template 企业指标模板
     * @return 变更数
     */
    int add(EnterpriseTargetTemplate template);

    /**
     * 修改
     *
     * @param template 企业指标模板
     * @return 变更数
     */
    int updateById(EnterpriseTargetTemplate template);

    /**
     * 查询（根据企业指标模板编号查询）
     *
     * @param targetTemplateId 企业指标模板编号
     * @return ReportTemplate
     */
    EnterpriseTargetTemplate findOneById(Integer targetTemplateId);

    /**
     * 删除：根据企业指标模板编号删除
     *
     * @param targetTemplateId 企业指标模板编号
     * @return 变更数
     */
    int deleteById(Integer targetTemplateId);
}
