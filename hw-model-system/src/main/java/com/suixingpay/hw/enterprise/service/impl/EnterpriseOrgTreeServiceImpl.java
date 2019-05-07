package com.suixingpay.hw.enterprise.service.impl;

import com.suixingpay.hw.enterprise.domain.EnterpriseOrgTree;
import com.suixingpay.hw.enterprise.mapper.EnterpriseOrgTreeMapper;
import com.suixingpay.hw.enterprise.service.IEnterpriseOrgTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-04-25 10:37
 **/
@Service
public class EnterpriseOrgTreeServiceImpl implements IEnterpriseOrgTreeService {

    @Autowired
    private EnterpriseOrgTreeMapper enterpriseOrgTreeMapper;

    @Override
    public List<EnterpriseOrgTree> find(EnterpriseOrgTree enterpriseOrgTree) {
        return enterpriseOrgTreeMapper.find(enterpriseOrgTree);
    }

    @Override
    public List<EnterpriseOrgTree> findByPlatformEntId(Integer platformEntId) {
        return enterpriseOrgTreeMapper.findByPlatformEntId(platformEntId);
    }
}
