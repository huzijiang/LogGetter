package com.suixingpay.hw.enterprise.service.impl;

import com.suixingpay.hw.enterprise.domain.EnterpriseJoinApply;
import com.suixingpay.hw.enterprise.mapper.EnterpriseJoinApplyMapper;
import com.suixingpay.hw.enterprise.service.IEnterpriseJoinApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-27 18:22
 **/
@Service
public class EnterpriseJoinApplyServiceImpl implements IEnterpriseJoinApplyService {
    @Autowired
    private EnterpriseJoinApplyMapper enterpriseJoinApplyMapper;

    @Override
    public EnterpriseJoinApply findOneById(Integer enterpriseJoinApplyId) {
        return enterpriseJoinApplyMapper.findOneById(enterpriseJoinApplyId);
    }

    @Override
    public List<EnterpriseJoinApply> find(EnterpriseJoinApply enterpriseJoinApply) {
        return enterpriseJoinApplyMapper.find(enterpriseJoinApply);
    }

    @Override
    public int add(EnterpriseJoinApply enterpriseJoinApply) {
        return enterpriseJoinApplyMapper.add(enterpriseJoinApply);
    }

    @Override
    public int updateById(EnterpriseJoinApply enterpriseJoinApply) {
        return enterpriseJoinApplyMapper.updateById(enterpriseJoinApply);
    }

    @Override
    public int deleteById(Integer enterpriseJoinApplyId) {
        return enterpriseJoinApplyMapper.deleteById(enterpriseJoinApplyId);
    }
}
