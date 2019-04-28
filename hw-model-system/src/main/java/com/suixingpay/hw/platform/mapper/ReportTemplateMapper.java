package com.suixingpay.hw.platform.mapper;

import com.suixingpay.hw.platform.domain.ReportTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 平台报告模型 mapper 接口
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-23 15:28
 **/
public interface ReportTemplateMapper {

    /**
     * 查询（根据实体查询）
     *
     * @param template 平台报告模型
     * @return 平台报告模型列表
     */
    List<ReportTemplate> find(ReportTemplate template);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param template 平台报告模型
     * @return 变更数
     */
    int add(ReportTemplate template);

    /**
     * 修改
     *
     * @param template 平台报告模型
     * @return 变更数
     */
    int updateByReportTemplateId(ReportTemplate template);

    /**
     * 查询（根据平台报告模型编号查询）
     *
     * @param reportTemplateId 平台报告模型编号
     * @return ReportTemplate
     */
    ReportTemplate findOneById(Integer reportTemplateId);

    /**
     * 删除：根据平台报告模型编号删除
     *
     * @param reportTemplateId 平台报告模型编号
     * @return 变更数
     */
    int deleteById(Integer reportTemplateId);

    /**
     * 根据平台报告模型编号获取其平台指标模型编号
     *
     * @param reportTemplateId 平台报告模型编号
     * @return 平台指标模型编号列表
     */
    List<Integer> selectTargetModelByReportTemplateId(Integer reportTemplateId);

    /**
     * 插入：平台报告模型与平台指标模型映射关系
     *
     * @param ids 平台指标模型编号
     * @param reportTemplateId 平台报告模型编号
     * @return
     */
    int insertReportTargetRelation(@Param("ids") Integer[] ids, @Param("reportTemplateId") Integer reportTemplateId);
}
