package com.suixingpay.hw.platform.mapper;

import com.suixingpay.hw.platform.domain.TargetModelContentTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 平台指标模型内容模板 Mapper
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-25 23:00
 **/
public interface TargetModelContentTemplateMapper {

    /**
     * 查询（根据实体查询）
     *
     * @param targetModelContentTemplate 平台指标模型内容模板
     * @return 平台指标模型内容模板列表
     */
    List<TargetModelContentTemplate> find(TargetModelContentTemplate targetModelContentTemplate);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param targetModelContentTemplate 平台指标模型内容模板
     * @return 变更数
     */
    int add(TargetModelContentTemplate targetModelContentTemplate);

    /**
     * 修改
     *
     * @param targetModelContentTemplate 平台指标模型内容模板
     * @return 变更数
     */
    int updateById(TargetModelContentTemplate targetModelContentTemplate);

    /**
     * 查询（根据平台指标模型内容模板编号查询）
     *
     * @param targetModelTemplateId 平台指标模型内容模板编号
     * @return 平台指标模型内容模板
     */
    TargetModelContentTemplate findOneById(Integer targetModelTemplateId);

    /**
     * 删除：根据指标模板编号删除
     *
     * @param targetModelTemplateId 平台指标模型内容模板编号
     * @return 变更数
     */
    int deleteById(Integer targetModelTemplateId);

    /**
     * 删除：根据平台指标模型内容模板编号删除
     *
     * @param ids 平台指标模型内容模板编号
     * @return 变更数
     */
    int deleteBatchIds(@Param("ids") Integer[] ids);

    /**
     * 查询（根据平台指标编号查询）
     *
     * @param targetModelId 平台指标编号
     * @return List<TargetModelContentTemplate>
     */
    List<TargetModelContentTemplate> selectByTargetModelId(Integer targetModelId);

    List<TargetModelContentTemplate> findList(TargetModelContentTemplate targetModelContentTemplate);
}
