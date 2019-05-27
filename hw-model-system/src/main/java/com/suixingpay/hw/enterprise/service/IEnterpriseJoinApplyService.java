package com.suixingpay.hw.enterprise.service;

import com.suixingpay.hw.enterprise.domain.EnterpriseJoinApply;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-27 18:22
 **/
public interface IEnterpriseJoinApplyService {

    EnterpriseJoinApply findOneById(Integer enterpriseJoinApplyId);

    List<EnterpriseJoinApply> find(EnterpriseJoinApply enterpriseJoinApply);

    int add(EnterpriseJoinApply enterpriseJoinApply);

    int updateById(EnterpriseJoinApply enterpriseJoinApply);

    int deleteById(Integer enterpriseJoinApplyId);
}
