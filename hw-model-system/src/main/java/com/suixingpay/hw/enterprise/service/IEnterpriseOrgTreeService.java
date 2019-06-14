package com.suixingpay.hw.enterprise.service;

import com.suixingpay.hw.enterprise.domain.EnterpriseOrgTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEnterpriseOrgTreeService {
    /**
     * 查询（根据实体查询）
     *
     * @param enterpriseOrgTree 企业组织结构
     * @return 企业组织结构列表
     */
    List<EnterpriseOrgTree> find(EnterpriseOrgTree enterpriseOrgTree);

    /**
     * 根据 平台编号 获取组织结构
     *
     * @param platformEntId 平台企业编号
     * @return List<EnterpriseOrgTree>
     */
    List<EnterpriseOrgTree> findByPlatformEntId(Integer platformEntId);

    List<EnterpriseOrgTree> findEntAndDept(@Param("platformEnterpriseId") Integer platformEntId);
}
