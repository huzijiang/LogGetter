package com.suixingpay.hw.enterprise.service.impl;

import com.suixingpay.hw.enterprise.domain.EnterpriseUser;
import com.suixingpay.hw.enterprise.mapper.EnterpriseUserMapper;
import com.suixingpay.hw.enterprise.service.IEnterpriseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xu_pf@suixingpay.com
 * @create: 2019-05-16 18:24
 **/
@Service
public class EnterpriseUserServiceImpl implements IEnterpriseUserService {
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;

    @Override
    public List<EnterpriseUser> findByEnterpriseId(Integer enterpriseId) {
        return enterpriseUserMapper.findByEnterpriseId(enterpriseId);
    }

    @Override
    public EnterpriseUser findByUserId(Integer entUserId) {
        return enterpriseUserMapper.findByUserId(entUserId);
    }
}
