package com.suixingpay.hw.system.service;

import com.suixingpay.hw.system.domain.Enterprise;
import com.suixingpay.hw.system.domain.EnterpriseUser;
import com.suixingpay.hw.system.domain.SysUser;

import java.util.List;

/**
 * @Classname IEnterpriseService
 * @Description 企业  业务层
 * @Date 2019/4/10 13:43
 * @Created liuyan[liu_yan@suixingpay.com]
 */
public interface IEnterpriseService
{
    /**
     * 根据条件分页查询企业对象
     *
     * @param enterprise 用户信息
     * @return 用户信息集合信息
     */
    public List<Enterprise> selectEnterpriseList(Enterprise enterprise);

    /**
     * 通过企业名查询用户
     *
     * @param name 企业名
     * @return 企业对象信息
     */
    public Enterprise selectEnterpriseByName(String name);

    /**
     * 通过信息查询用户
     *
     * @param enterprise 企业名
     * @return 企业对象信息
     */
    public List<Enterprise> selectEnterpriseByMessage(Enterprise enterprise);

    /**
     * 通过企业管理员编号查询企业
     *
     * @param admin 企业管理员编号
     * @return 企业对象信息
     */
    public Enterprise selectEnterpriseByAdmin(int admin);

    /**
     * 通过企业身份证号查询企业
     *
     * @param uscd 企业身份证号
     * @return 用户对象信息
     */
    public Enterprise selectEnterpriseByUscd(String uscd);

    /**
     * 通过企业ID查询企业
     *
     * @param enterpriseId 企业ID
     * @return 用企业对象信息
     */
    public Enterprise selectEnterpriseByEnterpriseId(int enterpriseId);

    /**
     * 通过企业ID删除企业
     *
     * @param enterpriseId 企业ID
     * @return 结果
     */
    public int deleteEnterpriseByEnterpriseId(int enterpriseId);

    public int deleteEnterpriseUserByEnterpriseId(int enterpriseId);


    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteEnterpriseByIds(String ids) throws Exception;

    /**
     * 保存企业信息
     *
     * @param Enterprise 企业信息
     * @return 结果
     */
    public int insertEnterprise(Enterprise enterprise);

    /**
     * 保存企业用户管理员信息
     *
     * @param enterpriseUser 企业用户管理员信息
     * @return 结果
     */
    public int insertEnterpriseUser(EnterpriseUser enterpriseUser);

//    /**
//     * 修改保存企业信息
//     *
//     * @param enterprise 企业信息
//     * @return 结果
//     */
//    public int updateEnterprise(Enterprise enterprise);

    /**
     * 修改用户详细信息
     *
     * @param enterprise 用户信息
     * @return 结果
     */
    public int updateEnterpriseInfo(Enterprise enterprise);

    /**
     * 校验企业id是否唯一
     *
     * @param enterpriseId 企业信息
     * @return 结果
     */
    public int checkEnterpriseIdUnique(int enterpriseId);


}
