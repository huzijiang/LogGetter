package com.suixingpay.hw.platform.mapper;

import com.suixingpay.hw.platform.domain.TargetModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TargetModelMapper {

    /**
     * 查询（根据实体查询）
     *
     * @param targetModel 指标模板
     * @return 报告模板列表
     */
    List<TargetModel> find(TargetModel targetModel);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param targetModel 指标模板
     * @return 变更数
     */
    int add(TargetModel targetModel);

    /**
     * 修改
     *
     * @param targetModel 指标模板
     * @return 变更数
     */
    int updateById(TargetModel targetModel);

    /**
     * 查询（根据指标模板编号查询）
     *
     * @param targetModelId 报告模板编号
     * @return TargetModel
     */
    TargetModel findOneById(Integer targetModelId);

    /**
     * 删除：根据指标模板编号删除
     *
     * @param targetModelId 指标模板编号
     * @return 变更数
     */
    int deleteById(Integer targetModelId);

    /**
     * 删除：根据指标模板编号删除关系表中数据
     *
     * @param targetModelId 指标模板编号
     * @return 变更数
     */
    int deleteRelationById(Integer targetModelId);

    /**
     * 删除：根据指标模板编号删除
     *
     * @param ids 指标模板编号
     * @return 变更数
     */
    int deleteBatchIds(@Param("ids") Integer[] ids);

    /**
     * 删除：根据指标模板编号删除关系表
     *
     * @param ids 指标模板编号
     * @return 变更数
     */
    int deleteRelationIds(@Param("ids") Integer[] ids);

    /**
     * 添加报告模板与指标模板映射关系
     *
     * @param reportTemplateId 报告模板编号
     * @param targetModelId 指标模板编号
     * @return
     */
    int addReportTargetRelation(@Param("reportTemplateId") Integer reportTemplateId,@Param("targetModelId") Integer targetModelId);

    /**
     * 根据 模型编号集合 查询
     *
     * @param targetModelIds 模型编号集合
     * @return List<TargetModel>
     */
    List<TargetModel> selectByIds(List<Integer> targetModelIds);
}