package com.suixingpay.hw.system.service.impl;

import com.suixingpay.hw.common.utils.PasswordEncoder;
import com.suixingpay.hw.common.utils.StringUtils;
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

    /** 默认密码 */
    private static final String DEFAULT_PASSWORD = "123456";

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
        enterpriseUser.setPassword(encoderPassword(enterpriseUser.getPassword()));
        return enterpriseMapper.insertEnterpriseUser(enterpriseUser);
    }

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

    /**
     * 密码处理方法
     *
     * @param passWord 明文密码
     * @return 密文密码
     */
    private String encoderPassword(String passWord) {
        PasswordEncoder encoder = new PasswordEncoder(PasswordEncoder.DEFAULT_SALT, "SHA");
        return encoder.encode(encoder.encode(StringUtils.isEmpty(passWord) ? DEFAULT_PASSWORD : passWord));
    }
}

