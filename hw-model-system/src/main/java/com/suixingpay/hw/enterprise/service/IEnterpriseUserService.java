package com.suixingpay.hw.enterprise.service;

import com.suixingpay.hw.enterprise.domain.EnterpriseUser;

import java.util.List;

public interface IEnterpriseUserService {

    List<EnterpriseUser> findByEnterpriseId(Integer enterpriseId);

    EnterpriseUser findByUserId(Integer entUserId);

}