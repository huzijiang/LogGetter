package com.suixingpay.hw.enterprise.mapper;

import com.suixingpay.hw.enterprise.domain.EnterpriseUser;

import java.util.List;

public interface EnterpriseUserMapper {

    List<EnterpriseUser> findByEnterpriseId(Integer enterpriseId);

    EnterpriseUser findByUserId(Integer entUserId);

}