package com.suixingpay.hw.enterprise.mapper;

import com.suixingpay.hw.enterprise.domain.EnterpriseOrgTree;

import java.util.List;

public interface EnterpriseOrgTreeMapper {
    /**
     * 查询（根据实体查询）
     *
     * @param enterpriseOrgTree 企业组织结构
     * @return 企业组织结构列表
     */
    List<EnterpriseOrgTree> find(EnterpriseOrgTree enterpriseOrgTree);
}
