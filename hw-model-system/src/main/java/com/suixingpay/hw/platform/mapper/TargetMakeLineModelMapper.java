package com.suixingpay.hw.platform.mapper;


import com.suixingpay.hw.platform.domain.TargetMakeLineModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TargetMakeLineModelMapper数据库操作接口类
 *
 * @author:  liuyan
 * @date: 2019-04-25
 * @version: V1.0
 * @review: liuyan/2019-04-25T10:13:59.354+08:00
 */
public interface TargetMakeLineModelMapper {

    /**
     * 查询（根据实体查询）
     *
     * @param makeLineModel 指标标记线模板
     * @return 指标标记线模板列表
     */
    List<TargetMakeLineModel> find(TargetMakeLineModel makeLineModel);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param makeLineModel 指标标记线模板
     * @return 变更数
     */
    int add(TargetMakeLineModel makeLineModel);

    /**
     * 修改
     *
     * @param makeLineModel 指标标记线模板
     * @return 变更数
     */
    int updateById(TargetMakeLineModel makeLineModel);

    /**
     * 查询（根据指标标记线模板编号查询）
     *
     * @param makeLineModelId 指标标记线模板
     * @return TargetMakeLineModel
     */
    TargetMakeLineModel findOneById(Integer makeLineModelId);

    /**
     * 删除：根据指标标记线模板编号删除
     *
     * @param makeLineModelId 指标标记线模板
     * @return 变更数
     */
    int deleteById(Integer makeLineModelId);

    /**
     * 删除：根据企业指标模板编号删除
     *
     * @param ids 企业指标模板编号
     * @return 变更数
     */
    int deleteBatchIds(@Param("ids") Integer[] ids);

    /**
     * 查询（根据指标展示模型编号）
     *
     * @param targetModelTempId 指标展示模型编号
     * @return List<TargetMakeLineModel>
     */
    List<TargetMakeLineModel> selectByTargetModelTempId(Integer targetModelTempId);
}