package com.suixingpay.hw.enterprise.mapper;

import com.suixingpay.hw.enterprise.domain.EnterpriseJoinApply;

import java.util.List;

public interface EnterpriseJoinApplyMapper {

    EnterpriseJoinApply findOneById(Integer enterpriseJoinApplyId);

    List<EnterpriseJoinApply> find(EnterpriseJoinApply enterpriseJoinApply);

    int add(EnterpriseJoinApply enterpriseJoinApply);

    int updateById(EnterpriseJoinApply enterpriseJoinApply);

    int deleteById(Integer enterpriseJoinApplyId);

}