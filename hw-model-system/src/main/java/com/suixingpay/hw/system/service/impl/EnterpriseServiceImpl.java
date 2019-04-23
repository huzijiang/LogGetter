package com.suixingpay.hw.system.service.impl;

import com.suixingpay.hw.common.annotation.DataScope;
import com.suixingpay.hw.system.domain.*;
import com.suixingpay.hw.system.mapper.*;
import com.suixingpay.hw.system.service.IEnterpriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Classname EnterpriseServiceImpl
 * @Description 企业  业务处理层
 * @Date 2019/4/10 13:45
 * @Created liuyan[liu_yan@suixingpay.com]
 */
@Service
public class EnterpriseServiceImpl implements IEnterpriseService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise) {
        return enterpriseMapper.selectEnterpriseList(enterprise);
    }

    @Override
    public Enterprise selectEnterpriseByName(String name) {
        return enterpriseMapper.selectEnterpriseByName(name);
    }

    @Override
    public Enterprise selectEnterpriseByAdmin(int admin) {
        return enterpriseMapper.selectEnterpriseByAdmin(admin);
    }

    @Override
    public Enterprise selectEnterpriseByUscd(String uscd) {
        return enterpriseMapper.selectEnterpriseByUscd(uscd);
    }

    @Override
    public Enterprise selectEnterpriseByEnterpriseId(int enterpriseId) {
        return enterpriseMapper.selectEnterpriseByEnterpriseId(enterpriseId);
    }

    @Override
    public int deleteEnterpriseByEnterpriseId(int enterpriseId) {
        return enterpriseMapper.deleteEnterpriseByEnterpriseId(enterpriseId);
    }

    @Override
    public int deleteEnterpriseUserByEnterpriseId(int enterpriseId) {
        return enterpriseMapper.deleteEnterpriseUserByEnterpriseId(enterpriseId);
    }

    @Override
    public int deleteEnterpriseByIds(String ids) throws Exception {
        return enterpriseMapper.deleteEnterpriseByIds(ids);
    }

    @Override
    public int insertEnterprise(Enterprise enterprise) {
        return enterpriseMapper.insertEnterprise(enterprise);
    }

    @Override
    public int insertEnterpriseUser(EnterpriseUser enterpriseUser) {
        return enterpriseMapper.insertEnterpriseUser(enterpriseUser);
    }

//    @Override
//    public int updateEnterprise(Enterprise enterprise) {
//        return EnterpriseMapper.updateEnterprise(enterprise);
//    }

    @Override
    public int updateEnterpriseInfo(Enterprise enterprise) {
        return enterpriseMapper.updateEnterpriseInfo(enterprise);
    }

    @Override
    public int checkEnterpriseIdUnique(int enterpriseId) {
        int count = enterpriseMapper.checkEnterpriseIdUnique(enterpriseId);
        if (count > 0)
        {
            return 1;
        }
        return 0;
    }
}

