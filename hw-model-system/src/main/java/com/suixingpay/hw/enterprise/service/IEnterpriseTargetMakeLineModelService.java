package com.suixingpay.hw.enterprise.service;

import com.suixingpay.hw.enterprise.domain.EnterpriseTargetMakeLineModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TargetMakeLineModelService业务实现类
 * 企业指标标记线模板业务实现类
 * @author:  liuyan
 * @date: 2019-04-25
 * @version: V1.0
 * @review: liuyan/2019-04-25T10:13:59.354+08:00
 */
public interface IEnterpriseTargetMakeLineModelService {

    /**
     * 查询（根据实体查询）
     *
     * @param makeLineModel 企业指标标记线模板
     * @return 企业指标标记线模板列表
     */
    List<EnterpriseTargetMakeLineModel> find(EnterpriseTargetMakeLineModel makeLineModel);

    /**
     * 添加 （匹配有值的字段）
     *
     * @param makeLineModel 企业指标标记线模板
     * @return 变更数
     */
    int add(EnterpriseTargetMakeLineModel makeLineModel);

    /**
     * 修改
     *
     * @param makeLineModel 企业指标标记线模板
     * @return 变更数
     */
    int updateById(EnterpriseTargetMakeLineModel makeLineModel);

    /**
     * 查询（根据企业指标标记线模板编号查询）
     *
     * @param makeLineModelId 企业指标标记线模板
     * @return EnterpriseTargetMakeLineModel
     */
    EnterpriseTargetMakeLineModel findOneById(Integer makeLineModelId);

    /**
     * 删除：根据企业指标标记线模板编号删除
     *
     * @param makeLineModelId 企业指标标记线模板
     * @return 变更数
     */
    int deleteById(Integer makeLineModelId);

    /**
     * 删除：根据指标标记线模板编号删除
     *
     * @param ids 指标标记线模板
     * @return 变更数
     */
    int deleteBatchIds(String ids);

    /**
     * 查询（根据企业指标模型编号）
     *
     * @param entTargetTempId 企业指标模型编号
     * @return List<EnterpriseTargetMakeLineModel>
     */
    List<EnterpriseTargetMakeLineModel> selectByEntTargetTempId(Integer entTargetTempId);

    /**
     * 查询所有
     *
     * @return List<EnterpriseTargetMakeLineModel>
     */
    List<EnterpriseTargetMakeLineModel> findAll();

}