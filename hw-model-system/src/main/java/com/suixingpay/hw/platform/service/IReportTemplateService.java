package com.suixingpay.hw.platform.service;

import com.suixingpay.hw.platform.domain.ReportTemplate;

import java.util.List;

/**
 * @description: 报告模板 service 接口
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 15:28
 **/
public interface IReportTemplateService {

    /**
     * 查询（根据实体查询）
     *
     * @param template 报告模板
     * @return 报告模板列表
     */
    List<ReportTemplate> find(ReportTemplate template);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param template 报告模板
     * @return 变更数
     */
    int add(ReportTemplate template);

    /**
     * 修改
     *
     * @param template 报告模板
     * @return 变更数
     */
    int updateByReportTemplateId(ReportTemplate template);

    /**
     * 查询（根据报告模板编号查询）
     *
     * @param reportTemplateId 报告模板编号
     * @return ReportTemplate
     */
    ReportTemplate findOneById(Integer reportTemplateId);

    /**
     * 删除：根据报告模板编号删除
     *
     * @param reportTemplateId 报告模板编号
     * @return 变更数
     */
    int deleteById(Integer reportTemplateId);

    /**
     * 根据报告模板编号获取其指标编号
     *
     * @param reportTemplateId
     * @return
     */
    List<Integer> selectTargetModelByReportTemplateId(Integer reportTemplateId);

}
